package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;

    private static class ElfError extends RuntimeException {
        ElfError(String why) {
            super(why);
        }
    }

    public static String[] extract_DT_NEEDED(File elfFile) throws IOException {
        FileInputStream is = new FileInputStream(elfFile);
        try {
            return extract_DT_NEEDED(is.getChannel());
        } finally {
            is.close();
        }
    }

    public static String[] extract_DT_NEEDED(FileChannel fc) throws IOException {
        long d_tag;
        long d_tag2;
        long sh_info;
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        if (getu32(fc, bb, 0) != 1179403647) {
            ElfError elfError = new ElfError("file is not ELF");
            throw elfError;
        }
        boolean is32 = getu8(fc, bb, 4) == 1;
        if (getu8(fc, bb, 5) == 2) {
            bb.order(ByteOrder.BIG_ENDIAN);
        }
        long e_phoff = is32 ? getu32(fc, bb, 28) : get64(fc, bb, 32);
        long e_phnum = is32 ? (long) getu16(fc, bb, 44) : (long) getu16(fc, bb, 56);
        int e_phentsize = is32 ? getu16(fc, bb, 42) : getu16(fc, bb, 54);
        if (e_phnum == 65535) {
            long e_shoff = is32 ? getu32(fc, bb, 32) : get64(fc, bb, 40);
            if (is32) {
                sh_info = getu32(fc, bb, 28 + e_shoff);
            } else {
                sh_info = getu32(fc, bb, 44 + e_shoff);
            }
            e_phnum = sh_info;
        }
        long dynStart = 0;
        long phdr = e_phoff;
        long i = 0;
        while (true) {
            if (i >= e_phnum) {
                break;
            }
            if ((is32 ? getu32(fc, bb, 0 + phdr) : getu32(fc, bb, 0 + phdr)) == 2) {
                dynStart = is32 ? getu32(fc, bb, 4 + phdr) : get64(fc, bb, 8 + phdr);
            } else {
                phdr += (long) e_phentsize;
                i++;
            }
        }
        if (dynStart == 0) {
            ElfError elfError2 = new ElfError("ELF file does not contain dynamic linking information");
            throw elfError2;
        }
        int nr_DT_NEEDED = 0;
        long dyn = dynStart;
        long ptr_DT_STRTAB = 0;
        do {
            d_tag = is32 ? getu32(fc, bb, 0 + dyn) : get64(fc, bb, 0 + dyn);
            if (d_tag == 1) {
                if (nr_DT_NEEDED == Integer.MAX_VALUE) {
                    ElfError elfError3 = new ElfError("malformed DT_NEEDED section");
                    throw elfError3;
                }
                nr_DT_NEEDED++;
            } else if (d_tag == 5) {
                ptr_DT_STRTAB = is32 ? getu32(fc, bb, 4 + dyn) : get64(fc, bb, 8 + dyn);
            }
            dyn += is32 ? 8 : 16;
        } while (d_tag != 0);
        if (ptr_DT_STRTAB == 0) {
            ElfError elfError4 = new ElfError("Dynamic section string-table not found");
            throw elfError4;
        }
        long off_DT_STRTAB = 0;
        long phdr2 = e_phoff;
        int i2 = 0;
        while (true) {
            if (((long) i2) >= e_phnum) {
                break;
            }
            if ((is32 ? getu32(fc, bb, 0 + phdr2) : getu32(fc, bb, 0 + phdr2)) == 1) {
                long p_vaddr = is32 ? getu32(fc, bb, 8 + phdr2) : get64(fc, bb, 16 + phdr2);
                long p_memsz = is32 ? getu32(fc, bb, 20 + phdr2) : get64(fc, bb, 40 + phdr2);
                if (p_vaddr <= ptr_DT_STRTAB && ptr_DT_STRTAB < p_vaddr + p_memsz) {
                    off_DT_STRTAB = (is32 ? getu32(fc, bb, 4 + phdr2) : get64(fc, bb, 8 + phdr2)) + (ptr_DT_STRTAB - p_vaddr);
                }
            }
            phdr2 += (long) e_phentsize;
            i2++;
        }
        if (off_DT_STRTAB == 0) {
            ElfError elfError5 = new ElfError("did not find file offset of DT_STRTAB table");
            throw elfError5;
        }
        String[] needed = new String[nr_DT_NEEDED];
        int nr_DT_NEEDED2 = 0;
        long dyn2 = dynStart;
        do {
            d_tag2 = is32 ? getu32(fc, bb, 0 + dyn2) : get64(fc, bb, 0 + dyn2);
            if (d_tag2 == 1) {
                needed[nr_DT_NEEDED2] = getSz(fc, bb, off_DT_STRTAB + (is32 ? getu32(fc, bb, 4 + dyn2) : get64(fc, bb, 8 + dyn2)));
                if (nr_DT_NEEDED2 == Integer.MAX_VALUE) {
                    ElfError elfError6 = new ElfError("malformed DT_NEEDED section");
                    throw elfError6;
                }
                nr_DT_NEEDED2++;
            }
            dyn2 += is32 ? 8 : 16;
        } while (d_tag2 != 0);
        if (nr_DT_NEEDED2 == needed.length) {
            return needed;
        }
        ElfError elfError7 = new ElfError("malformed DT_NEEDED section");
        throw elfError7;
    }

    private static String getSz(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long offset2 = offset + 1;
            short b = getu8(fc, bb, offset);
            if (b == 0) {
                return sb.toString();
            }
            sb.append((char) b);
            offset = offset2;
        }
    }

    private static void read(FileChannel fc, ByteBuffer bb, int sz, long offset) throws IOException {
        bb.position(0);
        bb.limit(sz);
        while (bb.remaining() > 0) {
            int numBytesRead = fc.read(bb, offset);
            if (numBytesRead == -1) {
                break;
            }
            offset += (long) numBytesRead;
        }
        if (bb.remaining() > 0) {
            throw new ElfError("ELF file truncated");
        }
        bb.position(0);
    }

    private static long get64(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        read(fc, bb, 8, offset);
        return bb.getLong();
    }

    private static long getu32(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        read(fc, bb, 4, offset);
        return ((long) bb.getInt()) & 4294967295L;
    }

    private static int getu16(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        read(fc, bb, 2, offset);
        return bb.getShort() & 65535;
    }

    private static short getu8(FileChannel fc, ByteBuffer bb, long offset) throws IOException {
        read(fc, bb, 1, offset);
        return (short) (bb.get() & 255);
    }
}
