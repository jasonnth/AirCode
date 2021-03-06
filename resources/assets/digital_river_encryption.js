!function () {
    function parseBigInt(a, b) {
        return new BigInteger(a, b)
    }

    function linebrk(a, b) {
        for (var c = "", d = 0; d + b < a.length;)c += a.substring(d, d + b) + "\n", d += b;
        return c + a.substring(d, a.length)
    }

    function byte2Hex(a) {
        return a < 16 ? "0" + a.toString(16) : a.toString(16)
    }

    function pkcs1pad2(a, b) {
        if (b < a.length + 11)return alert("Message too long for RSA"), null;
        for (var c = new Array, d = a.length - 1; d >= 0 && b > 0;) {
            var e = a.charCodeAt(d--);
            e < 128 ? c[--b] = e : e > 127 && e < 2048 ? (c[--b] = 63 & e | 128, c[--b] = e >> 6 | 192) : (c[--b] = 63 & e | 128, c[--b] = e >> 6 & 63 | 128, c[--b] = e >> 12 | 224)
        }
        c[--b] = 0;
        for (var f = new SecureRandom, g = new Array; b > 2;) {
            for (g[0] = 0; 0 == g[0];)f.nextBytes(g);
            c[--b] = g[0]
        }
        return c[--b] = 2, c[--b] = 0, new BigInteger(c)
    }

    function RSAKey() {
        this.n = null, this.e = 0, this.d = null, this.p = null, this.q = null, this.dmp1 = null, this.dmq1 = null, this.coeff = null
    }

    function RSASetPublic(a, b) {
        null != a && null != b && a.length > 0 && b.length > 0 ? (this.n = parseBigInt(a, 16), this.e = parseInt(b, 16)) : alert("Invalid RSA public key")
    }

    function RSADoPublic(a) {
        var b = a.modPowInt(this.e, this.n);
        return b
    }

    function RSAEncrypt(a) {
        var b = pkcs1pad2(a, this.n.bitLength() + 7 >> 3);
        if (null == b)return null;
        var c = this.doPublic(b);
        if (null == c)return null;
        var d = c.toString(16);
        return 0 == (1 & d.length) ? d : "0" + d
    }

    function RSAEncryptB64(a) {
        var b = this.encrypt(a);
        return b ? hex2b64(b) : null
    }

    function hex2b64(a) {
        var b, c, d = "";
        for (b = 0; b + 3 <= a.length; b += 3)c = parseInt(a.substring(b, b + 3), 16), d += b64map.charAt(c >> 6) + b64map.charAt(63 & c);
        for (b + 1 == a.length ? (c = parseInt(a.substring(b, b + 1), 16), d += b64map.charAt(c << 2)) : b + 2 == a.length && (c = parseInt(a.substring(b, b + 2), 16), d += b64map.charAt(c >> 2) + b64map.charAt((3 & c) << 4)); (3 & d.length) > 0;)d += b64padchar;
        return d
    }

    function b64tohex(a) {
        var c, e, b = "", d = 0;
        for (c = 0; c < a.length && a.charAt(c) != b64padchar; ++c)v = b64map.indexOf(a.charAt(c)), v < 0 || (0 == d ? (b += int2char(v >> 2), e = 3 & v, d = 1) : 1 == d ? (b += int2char(e << 2 | v >> 4), e = 15 & v, d = 2) : 2 == d ? (b += int2char(e), b += int2char(v >> 2), e = 3 & v, d = 3) : (b += int2char(e << 2 | v >> 4), b += int2char(15 & v), d = 0));
        return 1 == d && (b += int2char(e << 2)), b
    }

    function b64toBA(a) {
        var c, b = b64tohex(a), d = new Array;
        for (c = 0; 2 * c < b.length; ++c)d[c] = parseInt(b.substring(2 * c, 2 * c + 2), 16);
        return d
    }

    function BigInteger(a, b, c) {
        null != a && ("number" == typeof a ? this.fromNumber(a, b, c) : null == b && "string" != typeof a ? this.fromString(a, 256) : this.fromString(a, b))
    }

    function nbi() {
        return new BigInteger(null)
    }

    function am1(a, b, c, d, e, f) {
        for (; --f >= 0;) {
            var g = b * this[a++] + c[d] + e;
            e = Math.floor(g / 67108864), c[d++] = 67108863 & g
        }
        return e
    }

    function am2(a, b, c, d, e, f) {
        for (var g = 32767 & b, h = b >> 15; --f >= 0;) {
            var i = 32767 & this[a], j = this[a++] >> 15, k = h * i + j * g;
            i = g * i + ((32767 & k) << 15) + c[d] + (1073741823 & e), e = (i >>> 30) + (k >>> 15) + h * j + (e >>> 30), c[d++] = 1073741823 & i
        }
        return e
    }

    function am3(a, b, c, d, e, f) {
        for (var g = 16383 & b, h = b >> 14; --f >= 0;) {
            var i = 16383 & this[a], j = this[a++] >> 14, k = h * i + j * g;
            i = g * i + ((16383 & k) << 14) + c[d] + e, e = (i >> 28) + (k >> 14) + h * j, c[d++] = 268435455 & i
        }
        return e
    }

    function int2char(a) {
        return BI_RM.charAt(a)
    }

    function intAt(a, b) {
        var c = BI_RC[a.charCodeAt(b)];
        return null == c ? -1 : c
    }

    function bnpCopyTo(a) {
        for (var b = this.t - 1; b >= 0; --b)a[b] = this[b];
        a.t = this.t, a.s = this.s
    }

    function bnpFromInt(a) {
        this.t = 1, this.s = a < 0 ? -1 : 0, a > 0 ? this[0] = a : a < -1 ? this[0] = a + this.DV : this.t = 0
    }

    function nbv(a) {
        var b = nbi();
        return b.fromInt(a), b
    }

    function bnpFromString(a, b) {
        var c;
        if (16 == b) c = 4; else if (8 == b) c = 3; else if (256 == b) c = 8; else if (2 == b) c = 1; else if (32 == b) c = 5; else {
            if (4 != b)return void this.fromRadix(a, b);
            c = 2
        }
        this.t = 0, this.s = 0;
        for (var d = a.length, e = !1, f = 0; --d >= 0;) {
            var g = 8 == c ? 255 & a[d] : intAt(a, d);
            g < 0 ? "-" == a.charAt(d) && (e = !0) : (e = !1, 0 == f ? this[this.t++] = g : f + c > this.DB ? (this[this.t - 1] |= (g & (1 << this.DB - f) - 1) << f, this[this.t++] = g >> this.DB - f) : this[this.t - 1] |= g << f, f += c, f >= this.DB && (f -= this.DB))
        }
        8 == c && 0 != (128 & a[0]) && (this.s = -1, f > 0 && (this[this.t - 1] |= (1 << this.DB - f) - 1 << f)), this.clamp(), e && BigInteger.ZERO.subTo(this, this)
    }

    function bnpClamp() {
        for (var a = this.s & this.DM; this.t > 0 && this[this.t - 1] == a;)--this.t
    }

    function bnToString(a) {
        if (this.s < 0)return "-" + this.negate().toString(a);
        var b;
        if (16 == a) b = 4; else if (8 == a) b = 3; else if (2 == a) b = 1; else if (32 == a) b = 5; else {
            if (4 != a)return this.toRadix(a);
            b = 2
        }
        var d, c = (1 << b) - 1, e = !1, f = "", g = this.t, h = this.DB - g * this.DB % b;
        if (g-- > 0)for (h < this.DB && (d = this[g] >> h) > 0 && (e = !0, f = int2char(d)); g >= 0;)h < b ? (d = (this[g] & (1 << h) - 1) << b - h, d |= this[--g] >> (h += this.DB - b)) : (d = this[g] >> (h -= b) & c, h <= 0 && (h += this.DB, --g)), d > 0 && (e = !0), e && (f += int2char(d));
        return e ? f : "0"
    }

    function bnNegate() {
        var a = nbi();
        return BigInteger.ZERO.subTo(this, a), a
    }

    function bnAbs() {
        return this.s < 0 ? this.negate() : this
    }

    function bnCompareTo(a) {
        var b = this.s - a.s;
        if (0 != b)return b;
        var c = this.t;
        if (b = c - a.t, 0 != b)return this.s < 0 ? -b : b;
        for (; --c >= 0;)if (0 != (b = this[c] - a[c]))return b;
        return 0
    }

    function nbits(a) {
        var c, b = 1;
        return 0 != (c = a >>> 16) && (a = c, b += 16), 0 != (c = a >> 8) && (a = c, b += 8), 0 != (c = a >> 4) && (a = c, b += 4), 0 != (c = a >> 2) && (a = c, b += 2), 0 != (c = a >> 1) && (a = c, b += 1), b
    }

    function bnBitLength() {
        return this.t <= 0 ? 0 : this.DB * (this.t - 1) + nbits(this[this.t - 1] ^ this.s & this.DM)
    }

    function bnpDLShiftTo(a, b) {
        var c;
        for (c = this.t - 1; c >= 0; --c)b[c + a] = this[c];
        for (c = a - 1; c >= 0; --c)b[c] = 0;
        b.t = this.t + a, b.s = this.s
    }

    function bnpDRShiftTo(a, b) {
        for (var c = a; c < this.t; ++c)b[c - a] = this[c];
        b.t = Math.max(this.t - a, 0), b.s = this.s
    }

    function bnpLShiftTo(a, b) {
        var h, c = a % this.DB, d = this.DB - c, e = (1 << d) - 1, f = Math.floor(a / this.DB), g = this.s << c & this.DM;
        for (h = this.t - 1; h >= 0; --h)b[h + f + 1] = this[h] >> d | g, g = (this[h] & e) << c;
        for (h = f - 1; h >= 0; --h)b[h] = 0;
        b[f] = g, b.t = this.t + f + 1, b.s = this.s, b.clamp()
    }

    function bnpRShiftTo(a, b) {
        b.s = this.s;
        var c = Math.floor(a / this.DB);
        if (c >= this.t)return void(b.t = 0);
        var d = a % this.DB, e = this.DB - d, f = (1 << d) - 1;
        b[0] = this[c] >> d;
        for (var g = c + 1; g < this.t; ++g)b[g - c - 1] |= (this[g] & f) << e, b[g - c] = this[g] >> d;
        d > 0 && (b[this.t - c - 1] |= (this.s & f) << e), b.t = this.t - c, b.clamp()
    }

    function bnpSubTo(a, b) {
        for (var c = 0, d = 0, e = Math.min(a.t, this.t); c < e;)d += this[c] - a[c], b[c++] = d & this.DM, d >>= this.DB;
        if (a.t < this.t) {
            for (d -= a.s; c < this.t;)d += this[c], b[c++] = d & this.DM, d >>= this.DB;
            d += this.s
        } else {
            for (d += this.s; c < a.t;)d -= a[c], b[c++] = d & this.DM, d >>= this.DB;
            d -= a.s
        }
        b.s = d < 0 ? -1 : 0, d < -1 ? b[c++] = this.DV + d : d > 0 && (b[c++] = d), b.t = c, b.clamp()
    }

    function bnpMultiplyTo(a, b) {
        var c = this.abs(), d = a.abs(), e = c.t;
        for (b.t = e + d.t; --e >= 0;)b[e] = 0;
        for (e = 0; e < d.t; ++e)b[e + c.t] = c.am(0, d[e], b, e, 0, c.t);
        b.s = 0, b.clamp(), this.s != a.s && BigInteger.ZERO.subTo(b, b)
    }

    function bnpSquareTo(a) {
        for (var b = this.abs(), c = a.t = 2 * b.t; --c >= 0;)a[c] = 0;
        for (c = 0; c < b.t - 1; ++c) {
            var d = b.am(c, b[c], a, 2 * c, 0, 1);
            (a[c + b.t] += b.am(c + 1, 2 * b[c], a, 2 * c + 1, d, b.t - c - 1)) >= b.DV && (a[c + b.t] -= b.DV, a[c + b.t + 1] = 1)
        }
        a.t > 0 && (a[a.t - 1] += b.am(c, b[c], a, 2 * c, 0, 1)), a.s = 0, a.clamp()
    }

    function bnpDivRemTo(a, b, c) {
        var d = a.abs();
        if (!(d.t <= 0)) {
            var e = this.abs();
            if (e.t < d.t)return null != b && b.fromInt(0), void(null != c && this.copyTo(c));
            null == c && (c = nbi());
            var f = nbi(), g = this.s, h = a.s, i = this.DB - nbits(d[d.t - 1]);
            i > 0 ? (d.lShiftTo(i, f), e.lShiftTo(i, c)) : (d.copyTo(f), e.copyTo(c));
            var j = f.t, k = f[j - 1];
            if (0 != k) {
                var l = k * (1 << this.F1) + (j > 1 ? f[j - 2] >> this.F2 : 0), m = this.FV / l, n = (1 << this.F1) / l, o = 1 << this.F2, p = c.t, q = p - j, r = null == b ? nbi() : b;
                for (f.dlShiftTo(q, r), c.compareTo(r) >= 0 && (c[c.t++] = 1, c.subTo(r, c)), BigInteger.ONE.dlShiftTo(j, r), r.subTo(f, f); f.t < j;)f[f.t++] = 0;
                for (; --q >= 0;) {
                    var s = c[--p] == k ? this.DM : Math.floor(c[p] * m + (c[p - 1] + o) * n);
                    if ((c[p] += f.am(0, s, c, q, 0, j)) < s)for (f.dlShiftTo(q, r), c.subTo(r, c); c[p] < --s;)c.subTo(r, c)
                }
                null != b && (c.drShiftTo(j, b), g != h && BigInteger.ZERO.subTo(b, b)), c.t = j, c.clamp(), i > 0 && c.rShiftTo(i, c), g < 0 && BigInteger.ZERO.subTo(c, c)
            }
        }
    }

    function bnMod(a) {
        var b = nbi();
        return this.abs().divRemTo(a, null, b), this.s < 0 && b.compareTo(BigInteger.ZERO) > 0 && a.subTo(b, b), b
    }

    function Classic(a) {
        this.m = a
    }

    function cConvert(a) {
        return a.s < 0 || a.compareTo(this.m) >= 0 ? a.mod(this.m) : a
    }

    function cRevert(a) {
        return a
    }

    function cReduce(a) {
        a.divRemTo(this.m, null, a)
    }

    function cMulTo(a, b, c) {
        a.multiplyTo(b, c), this.reduce(c)
    }

    function cSqrTo(a, b) {
        a.squareTo(b), this.reduce(b)
    }

    function bnpInvDigit() {
        if (this.t < 1)return 0;
        var a = this[0];
        if (0 == (1 & a))return 0;
        var b = 3 & a;
        return b = b * (2 - (15 & a) * b) & 15, b = b * (2 - (255 & a) * b) & 255, b = b * (2 - ((65535 & a) * b & 65535)) & 65535, b = b * (2 - a * b % this.DV) % this.DV, b > 0 ? this.DV - b : -b
    }

    function Montgomery(a) {
        this.m = a, this.mp = a.invDigit(), this.mpl = 32767 & this.mp, this.mph = this.mp >> 15, this.um = (1 << a.DB - 15) - 1, this.mt2 = 2 * a.t
    }

    function montConvert(a) {
        var b = nbi();
        return a.abs().dlShiftTo(this.m.t, b), b.divRemTo(this.m, null, b), a.s < 0 && b.compareTo(BigInteger.ZERO) > 0 && this.m.subTo(b, b), b
    }

    function montRevert(a) {
        var b = nbi();
        return a.copyTo(b), this.reduce(b), b
    }

    function montReduce(a) {
        for (; a.t <= this.mt2;)a[a.t++] = 0;
        for (var b = 0; b < this.m.t; ++b) {
            var c = 32767 & a[b], d = c * this.mpl + ((c * this.mph + (a[b] >> 15) * this.mpl & this.um) << 15) & a.DM;
            for (c = b + this.m.t, a[c] += this.m.am(0, d, a, b, 0, this.m.t); a[c] >= a.DV;)a[c] -= a.DV, a[++c]++
        }
        a.clamp(), a.drShiftTo(this.m.t, a), a.compareTo(this.m) >= 0 && a.subTo(this.m, a)
    }

    function montSqrTo(a, b) {
        a.squareTo(b), this.reduce(b)
    }

    function montMulTo(a, b, c) {
        a.multiplyTo(b, c), this.reduce(c)
    }

    function bnpIsEven() {
        return 0 == (this.t > 0 ? 1 & this[0] : this.s)
    }

    function bnpExp(a, b) {
        if (a > 4294967295 || a < 1)return BigInteger.ONE;
        var c = nbi(), d = nbi(), e = b.convert(this), f = nbits(a) - 1;
        for (e.copyTo(c); --f >= 0;)if (b.sqrTo(c, d), (a & 1 << f) > 0) b.mulTo(d, e, c); else {
            var g = c;
            c = d, d = g
        }
        return b.revert(c)
    }

    function bnModPowInt(a, b) {
        var c;
        return c = a < 256 || b.isEven() ? new Classic(b) : new Montgomery(b), this.exp(a, c)
    }

    function rng_seed_int(a) {
        rng_pool[rng_pptr++] ^= 255 & a, rng_pool[rng_pptr++] ^= a >> 8 & 255, rng_pool[rng_pptr++] ^= a >> 16 & 255, rng_pool[rng_pptr++] ^= a >> 24 & 255, rng_pptr >= rng_psize && (rng_pptr -= rng_psize)
    }

    function rng_seed_time() {
        rng_seed_int((new Date).getTime())
    }

    function rng_get_byte() {
        if (null == rng_state) {
            for (rng_seed_time(), rng_state = prng_newstate(), rng_state.init(rng_pool), rng_pptr = 0; rng_pptr < rng_pool.length; ++rng_pptr)rng_pool[rng_pptr] = 0;
            rng_pptr = 0
        }
        return rng_state.next()
    }

    function rng_get_bytes(a) {
        var b;
        for (b = 0; b < a.length; ++b)a[b] = rng_get_byte()
    }

    function SecureRandom() {
    }

    function Arcfour() {
        this.i = 0, this.j = 0, this.S = new Array
    }

    function ARC4init(a) {
        var b, c, d;
        for (b = 0; b < 256; ++b)this.S[b] = b;
        for (c = 0, b = 0; b < 256; ++b)c = c + this.S[b] + a[b % a.length] & 255, d = this.S[b], this.S[b] = this.S[c], this.S[c] = d;
        this.i = 0, this.j = 0
    }

    function ARC4next() {
        var a;
        return this.i = this.i + 1 & 255, this.j = this.j + this.S[this.i] & 255, a = this.S[this.i], this.S[this.i] = this.S[this.j], this.S[this.j] = a, this.S[a + this.S[this.i] & 255]
    }

    function prng_newstate() {
        return new Arcfour
    }

    function q(a) {
        throw a
    }

    function y(a, b, c) {
        4 !== b.length && q(new sjcl.exception.invalid("invalid aes block size"));
        var d = a.b[c], e = b[0] ^ d[0], f = b[c ? 3 : 1] ^ d[1], g = b[2] ^ d[2];
        b = b[c ? 1 : 3] ^ d[3];
        var h, i, j, l, k = d.length / 4 - 2, m = 4, n = [0, 0, 0, 0];
        h = a.k[c], a = h[0];
        var o = h[1], p = h[2], r = h[3], s = h[4];
        for (l = 0; l < k; l++)h = a[e >>> 24] ^ o[f >> 16 & 255] ^ p[g >> 8 & 255] ^ r[255 & b] ^ d[m], i = a[f >>> 24] ^ o[g >> 16 & 255] ^ p[b >> 8 & 255] ^ r[255 & e] ^ d[m + 1], j = a[g >>> 24] ^ o[b >> 16 & 255] ^ p[e >> 8 & 255] ^ r[255 & f] ^ d[m + 2], b = a[b >>> 24] ^ o[e >> 16 & 255] ^ p[f >> 8 & 255] ^ r[255 & g] ^ d[m + 3], m += 4, e = h, f = i, g = j;
        for (l = 0; 4 > l; l++)n[c ? 3 & -l : l] = s[e >>> 24] << 24 ^ s[f >> 16 & 255] << 16 ^ s[g >> 8 & 255] << 8 ^ s[255 & b] ^ d[m++], h = e, e = f, f = g, g = b, b = h;
        return n
    }

    function z(a, b) {
        var c, d, e, f = b.slice(0), g = a.r, h = a.b, i = g[0], j = g[1], k = g[2], l = g[3], m = g[4], n = g[5], o = g[6], p = g[7];
        for (c = 0; 64 > c; c++)16 > c ? d = f[c] : (d = f[c + 1 & 15], e = f[c + 14 & 15], d = f[15 & c] = (d >>> 7 ^ d >>> 18 ^ d >>> 3 ^ d << 25 ^ d << 14) + (e >>> 17 ^ e >>> 19 ^ e >>> 10 ^ e << 15 ^ e << 13) + f[15 & c] + f[c + 9 & 15] | 0), d = d + p + (m >>> 6 ^ m >>> 11 ^ m >>> 25 ^ m << 26 ^ m << 21 ^ m << 7) + (o ^ m & (n ^ o)) + h[c], p = o, o = n, n = m, m = l + d | 0, l = k, k = j, j = i, i = d + (j & k ^ l & (j ^ k)) + (j >>> 2 ^ j >>> 13 ^ j >>> 22 ^ j << 30 ^ j << 19 ^ j << 10) | 0;
        g[0] = g[0] + i | 0, g[1] = g[1] + j | 0, g[2] = g[2] + k | 0, g[3] = g[3] + l | 0, g[4] = g[4] + m | 0, g[5] = g[5] + n | 0, g[6] = g[6] + o | 0, g[7] = g[7] + p | 0
    }

    function C(a, b) {
        var c, d = sjcl.random.w[a], e = [];
        for (c in d)d.hasOwnProperty(c) && e.push(d[c]);
        for (c = 0; c < e.length; c++)e[c](b)
    }

    function E(a) {
        "undefined" != typeof window && window.performance && "function" == typeof window.performance.now ? sjcl.random.addEntropy(window.performance.now(), a, "loadtime") : sjcl.random.addEntropy((new Date).valueOf(), a, "loadtime")
    }

    function A(a) {
        a.b = B(a).concat(B(a)), a.A = new sjcl.cipher.aes(a.b)
    }

    function B(a) {
        for (var b = 0; 4 > b && (a.f[b] = a.f[b] + 1 | 0, !a.f[b]); b++);
        return a.A.encrypt(a.f)
    }

    function D(a, b) {
        return function () {
            b.apply(a, arguments)
        }
    }

    RSAKey.prototype.doPublic = RSADoPublic, RSAKey.prototype.setPublic = RSASetPublic, RSAKey.prototype.encrypt = RSAEncrypt, RSAKey.prototype.encrypt_b64 = RSAEncryptB64;
    var b64map = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", b64padchar = "=", dbits, canary = 0xdeadbeefcafe, j_lm = 15715070 == (16777215 & canary);
    j_lm && "Microsoft Internet Explorer" == navigator.appName ? (BigInteger.prototype.am = am2, dbits = 30) : j_lm && "Netscape" != navigator.appName ? (BigInteger.prototype.am = am1, dbits = 26) : (BigInteger.prototype.am = am3, dbits = 28), BigInteger.prototype.DB = dbits, BigInteger.prototype.DM = (1 << dbits) - 1, BigInteger.prototype.DV = 1 << dbits;
    var BI_FP = 52;
    BigInteger.prototype.FV = Math.pow(2, BI_FP), BigInteger.prototype.F1 = BI_FP - dbits, BigInteger.prototype.F2 = 2 * dbits - BI_FP;
    var BI_RM = "0123456789abcdefghijklmnopqrstuvwxyz", BI_RC = new Array, rr, vv;
    for (rr = "0".charCodeAt(0), vv = 0; vv <= 9; ++vv)BI_RC[rr++] = vv;
    for (rr = "a".charCodeAt(0), vv = 10; vv < 36; ++vv)BI_RC[rr++] = vv;
    for (rr = "A".charCodeAt(0), vv = 10; vv < 36; ++vv)BI_RC[rr++] = vv;
    Classic.prototype.convert = cConvert, Classic.prototype.revert = cRevert, Classic.prototype.reduce = cReduce, Classic.prototype.mulTo = cMulTo, Classic.prototype.sqrTo = cSqrTo, Montgomery.prototype.convert = montConvert, Montgomery.prototype.revert = montRevert, Montgomery.prototype.reduce = montReduce, Montgomery.prototype.mulTo = montMulTo, Montgomery.prototype.sqrTo = montSqrTo, BigInteger.prototype.copyTo = bnpCopyTo, BigInteger.prototype.fromInt = bnpFromInt, BigInteger.prototype.fromString = bnpFromString, BigInteger.prototype.clamp = bnpClamp, BigInteger.prototype.dlShiftTo = bnpDLShiftTo, BigInteger.prototype.drShiftTo = bnpDRShiftTo, BigInteger.prototype.lShiftTo = bnpLShiftTo, BigInteger.prototype.rShiftTo = bnpRShiftTo, BigInteger.prototype.subTo = bnpSubTo, BigInteger.prototype.multiplyTo = bnpMultiplyTo, BigInteger.prototype.squareTo = bnpSquareTo, BigInteger.prototype.divRemTo = bnpDivRemTo, BigInteger.prototype.invDigit = bnpInvDigit, BigInteger.prototype.isEven = bnpIsEven, BigInteger.prototype.exp = bnpExp, BigInteger.prototype.toString = bnToString, BigInteger.prototype.negate = bnNegate, BigInteger.prototype.abs = bnAbs, BigInteger.prototype.compareTo = bnCompareTo, BigInteger.prototype.bitLength = bnBitLength, BigInteger.prototype.mod = bnMod, BigInteger.prototype.modPowInt = bnModPowInt, BigInteger.ZERO = nbv(0), BigInteger.ONE = nbv(1);
    var rng_state, rng_pool, rng_pptr;
    if (null == rng_pool) {
        rng_pool = [], rng_pptr = 0;
        var t;
        try {
            if (window.crypto && window.crypto.getRandomValues) {
                var ua = new Uint8Array(32);
                for (window.crypto.getRandomValues(ua), t = 0; t < 32; ++t)rng_pool[rng_pptr++] = ua[t]
            } else if (window.msCrypto && window.msCrypto.getRandomValues) {
                var ua = new Uint8Array(32);
                for (window.msCrypto.getRandomValues(ua), t = 0; t < 32; ++t)rng_pool[rng_pptr++] = ua[t]
            } else if (window.crypto && window.crypto.random) {
                var z = window.crypto.random(32);
                for (t = 0; t < z.length; ++t)rng_pool[rng_pptr++] = 255 & z.charCodeAt(t)
            }
        } catch (a) {
        }
        for (; rng_pptr < rng_psize;)t = Math.floor(65536 * Math.random()), rng_pool[rng_pptr++] = t >>> 8, rng_pool[rng_pptr++] = 255 & t;
        rng_pptr = 0, rng_seed_time()
    }
    SecureRandom.prototype.nextBytes = rng_get_bytes, function () {
        function b(a, b) {
            return this.slice(a, b)
        }

        function c(a, b) {
            arguments.length < 2 && (b = 0);
            for (var c = 0, d = a.length; c < d; ++c, ++b)this[b] = 255 & a[c]
        }

        function d(a) {
            var d;
            if ("number" == typeof a) {
                d = new Array(a);
                for (var e = 0; e < a; ++e)d[e] = 0
            } else d = a.slice(0);
            return d.subarray = b, d.buffer = d, d.byteLength = d.length, d.set = c, "object" == typeof a && a.buffer && (d.buffer = a.buffer), d
        }

        try {
            [new Uint8Array(1), new Uint32Array(1), new Int32Array(1)];
            return
        } catch (a) {
        }
        try {
            window.Uint8Array = d
        } catch (a) {
        }
        try {
            window.Uint32Array = d
        } catch (a) {
        }
        try {
            window.Int32Array = d
        } catch (a) {
        }
    }(), function () {
        if (!("response" in XMLHttpRequest.prototype || "mozResponseArrayBuffer" in XMLHttpRequest.prototype || "mozResponse" in XMLHttpRequest.prototype || "responseArrayBuffer" in XMLHttpRequest.prototype))try {
            Object.defineProperty(XMLHttpRequest.prototype, "response", {
                get: function () {
                    return new Uint8Array(new VBArray(this.responseBody).toArray())
                }
            })
        } catch (a) {
        }
    }(), function () {
        if (!("btoa" in window)) {
            var a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
            window.btoa = function (b) {
                var d, e, c = "";
                for (d = 0, e = b.length; d < e; d += 3) {
                    var f = 255 & b.charCodeAt(d), g = 255 & b.charCodeAt(d + 1), h = 255 & b.charCodeAt(d + 2), i = f >> 2, j = (3 & f) << 4 | g >> 4, k = d + 1 < e ? (15 & g) << 2 | h >> 6 : 64, l = d + 2 < e ? 63 & h : 64;
                    c += a.charAt(i) + a.charAt(j) + a.charAt(k) + a.charAt(l)
                }
                return c
            }
        }
    }(), "object" != typeof JSON && (JSON = {}), function () {
        function f(a) {
            return a < 10 ? "0" + a : a
        }

        function quote(a) {
            return escapable.lastIndex = 0, escapable.test(a) ? '"' + a.replace(escapable, function (a) {
                    var b = meta[a];
                    return "string" == typeof b ? b : "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
                }) + '"' : '"' + a + '"'
        }

        function str(a, b) {
            var c, d, e, f, h, g = gap, i = b[a];
            switch (i && "object" == typeof i && "function" == typeof i.toJSON && (i = i.toJSON(a)), "function" == typeof rep && (i = rep.call(b, a, i)), typeof i) {
                case"string":
                    return quote(i);
                case"number":
                    return isFinite(i) ? String(i) : "null";
                case"boolean":
                case"null":
                    return String(i);
                case"object":
                    if (!i)return "null";
                    if (gap += indent, h = [], "[object Array]" === Object.prototype.toString.apply(i)) {
                        for (f = i.length, c = 0; c < f; c += 1)h[c] = str(c, i) || "null";
                        return e = 0 === h.length ? "[]" : gap ? "[\n" + gap + h.join(",\n" + gap) + "\n" + g + "]" : "[" + h.join(",") + "]", gap = g, e
                    }
                    if (rep && "object" == typeof rep)for (f = rep.length, c = 0; c < f; c += 1)"string" == typeof rep[c] && (d = rep[c], e = str(d, i), e && h.push(quote(d) + (gap ? ": " : ":") + e)); else for (d in i)Object.prototype.hasOwnProperty.call(i, d) && (e = str(d, i), e && h.push(quote(d) + (gap ? ": " : ":") + e));
                    return e = 0 === h.length ? "{}" : gap ? "{\n" + gap + h.join(",\n" + gap) + "\n" + g + "}" : "{" + h.join(",") + "}", gap = g, e
            }
        }

        "function" != typeof Date.prototype.toJSON && (Date.prototype.toJSON = function (a) {
            return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-" + f(this.getUTCMonth() + 1) + "-" + f(this.getUTCDate()) + "T" + f(this.getUTCHours()) + ":" + f(this.getUTCMinutes()) + ":" + f(this.getUTCSeconds()) + "Z" : null
        }, String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function (a) {
            return this.valueOf()
        });
        var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, gap, indent, meta = {
            "\b": "\\b",
            "\t": "\\t",
            "\n": "\\n",
            "\f": "\\f",
            "\r": "\\r",
            '"': '\\"',
            "\\": "\\\\"
        }, rep;
        "function" != typeof JSON.stringify && (JSON.stringify = function (a, b, c) {
            var d;
            if (gap = "", indent = "", "number" == typeof c)for (d = 0; d < c; d += 1)indent += " "; else"string" == typeof c && (indent = c);
            if (rep = b, b && "function" != typeof b && ("object" != typeof b || "number" != typeof b.length))throw new Error("JSON.stringify");
            return str("", {"": a})
        }), "function" != typeof JSON.parse && (JSON.parse = function (text, reviver) {
            function walk(a, b) {
                var c, d, e = a[b];
                if (e && "object" == typeof e)for (c in e)Object.prototype.hasOwnProperty.call(e, c) && (d = walk(e, c), void 0 !== d ? e[c] = d : delete e[c]);
                return reviver.call(a, b, e)
            }

            var j;
            if (text = String(text), cx.lastIndex = 0, cx.test(text) && (text = text.replace(cx, function (a) {
                    return "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
                })), /^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, "")))return j = eval("(" + text + ")"), "function" == typeof reviver ? walk({"": j}, "") : j;
            throw new SyntaxError("JSON.parse")
        })
    }(), Arcfour.prototype.init = ARC4init, Arcfour.prototype.next = ARC4next;
    var rng_psize = 256, t = void 0, u = !1, sjcl = {
        cipher: {},
        hash: {},
        keyexchange: {},
        mode: {},
        misc: {},
        codec: {},
        exception: {
            corrupt: function (a) {
                this.toString = function () {
                    return "CORRUPT: " + this.message
                }, this.message = a
            }, invalid: function (a) {
                this.toString = function () {
                    return "INVALID: " + this.message
                }, this.message = a
            }, bug: function (a) {
                this.toString = function () {
                    return "BUG: " + this.message
                }, this.message = a
            }, notReady: function (a) {
                this.toString = function () {
                    return "NOT READY: " + this.message
                }, this.message = a
            }
        }
    };
    "undefined" != typeof module && module.exports && (module.exports = sjcl), "function" == typeof define && define([], function () {
        return sjcl
    }), sjcl.cipher.aes = function (a) {
        this.k[0][0][0] || this.D();
        var b, c, d, e, f = this.k[0][4], g = this.k[1];
        b = a.length;
        var h = 1;
        for (4 !== b && 6 !== b && 8 !== b && q(new sjcl.exception.invalid("invalid aes key size")), this.b = [d = a.slice(0), e = []], a = b; a < 4 * b + 28; a++)c = d[a - 1], (0 === a % b || 8 === b && 4 === a % b) && (c = f[c >>> 24] << 24 ^ f[c >> 16 & 255] << 16 ^ f[c >> 8 & 255] << 8 ^ f[255 & c], 0 === a % b && (c = c << 8 ^ c >>> 24 ^ h << 24, h = h << 1 ^ 283 * (h >> 7))), d[a] = d[a - b] ^ c;
        for (b = 0; a; b++, a--)c = d[3 & b ? a : a - 4], e[b] = 4 >= a || 4 > b ? c : g[0][f[c >>> 24]] ^ g[1][f[c >> 16 & 255]] ^ g[2][f[c >> 8 & 255]] ^ g[3][f[255 & c]]
    }, sjcl.cipher.aes.prototype = {
        encrypt: function (a) {
            return y(this, a, 0)
        }, decrypt: function (a) {
            return y(this, a, 1)
        }, k: [[[], [], [], [], []], [[], [], [], [], []]], D: function () {
            var e, f, g, j, k, l, m, a = this.k[0], b = this.k[1], c = a[4], d = b[4], h = [], i = [];
            for (e = 0; 256 > e; e++)i[(h[e] = e << 1 ^ 283 * (e >> 7)) ^ e] = e;
            for (f = g = 0; !c[f]; f ^= j || 1, g = i[g] || 1)for (l = g ^ g << 1 ^ g << 2 ^ g << 3 ^ g << 4, l = l >> 8 ^ 255 & l ^ 99, c[f] = l, d[l] = f, k = h[e = h[j = h[f]]], m = 16843009 * k ^ 65537 * e ^ 257 * j ^ 16843008 * f, k = 257 * h[l] ^ 16843008 * l, e = 0; 4 > e; e++)a[e][f] = k = k << 24 ^ k >>> 8, b[e][l] = m = m << 24 ^ m >>> 8;
            for (e = 0; 5 > e; e++)a[e] = a[e].slice(0), b[e] = b[e].slice(0)
        }
    }, sjcl.bitArray = {
        bitSlice: function (a, b, c) {
            return a = sjcl.bitArray.P(a.slice(b / 32), 32 - (31 & b)).slice(1), c === t ? a : sjcl.bitArray.clamp(a, c - b)
        }, extract: function (a, b, c) {
            var d = Math.floor(-b - c & 31);
            return ((b + c - 1 ^ b) & -32 ? a[b / 32 | 0] << 32 - d ^ a[b / 32 + 1 | 0] >>> d : a[b / 32 | 0] >>> d) & (1 << c) - 1
        }, concat: function (a, b) {
            if (0 === a.length || 0 === b.length)return a.concat(b);
            var c = a[a.length - 1], d = sjcl.bitArray.getPartial(c);
            return 32 === d ? a.concat(b) : sjcl.bitArray.P(b, d, 0 | c, a.slice(0, a.length - 1))
        }, bitLength: function (a) {
            var b = a.length;
            return 0 === b ? 0 : 32 * (b - 1) + sjcl.bitArray.getPartial(a[b - 1])
        }, clamp: function (a, b) {
            if (32 * a.length < b)return a;
            a = a.slice(0, Math.ceil(b / 32));
            var c = a.length;
            return b &= 31, 0 < c && b && (a[c - 1] = sjcl.bitArray.partial(b, a[c - 1] & 2147483648 >> b - 1, 1)), a
        }, partial: function (a, b, c) {
            return 32 === a ? b : (c ? 0 | b : b << 32 - a) + 1099511627776 * a
        }, getPartial: function (a) {
            return Math.round(a / 1099511627776) || 32
        }, equal: function (a, b) {
            if (sjcl.bitArray.bitLength(a) !== sjcl.bitArray.bitLength(b))return u;
            var d, c = 0;
            for (d = 0; d < a.length; d++)c |= a[d] ^ b[d];
            return 0 === c
        }, P: function (a, b, c, d) {
            var e;
            for (e = 0, d === t && (d = []); 32 <= b; b -= 32)d.push(c), c = 0;
            if (0 === b)return d.concat(a);
            for (e = 0; e < a.length; e++)d.push(c | a[e] >>> b), c = a[e] << 32 - b;
            return e = a.length ? a[a.length - 1] : 0, a = sjcl.bitArray.getPartial(e), d.push(sjcl.bitArray.partial(b + a & 31, 32 < b + a ? c : d.pop(), 1)), d
        }, l: function (a, b) {
            return [a[0] ^ b[0], a[1] ^ b[1], a[2] ^ b[2], a[3] ^ b[3]]
        }, byteswapM: function (a) {
            var b, c;
            for (b = 0; b < a.length; ++b)c = a[b], a[b] = c >>> 24 | c >>> 8 & 65280 | (65280 & c) << 8 | c << 24;
            return a
        }
    }, sjcl.codec.utf8String = {
        fromBits: function (a) {
            var d, e, b = "", c = sjcl.bitArray.bitLength(a);
            for (d = 0; d < c / 8; d++)0 === (3 & d) && (e = a[d / 4]), b += String.fromCharCode(e >>> 24), e <<= 8;
            return decodeURIComponent(escape(b))
        }, toBits: function (a) {
            a = unescape(encodeURIComponent(a));
            var c, b = [], d = 0;
            for (c = 0; c < a.length; c++)d = d << 8 | a.charCodeAt(c), 3 === (3 & c) && (b.push(d), d = 0);
            return 3 & c && b.push(sjcl.bitArray.partial(8 * (3 & c), d)), b
        }
    }, sjcl.codec.hex = {
        fromBits: function (a) {
            var c, b = "";
            for (c = 0; c < a.length; c++)b += ((0 | a[c]) + 0xf00000000000).toString(16).substr(4);
            return b.substr(0, sjcl.bitArray.bitLength(a) / 4)
        }, toBits: function (a) {
            var b, d, c = [];
            for (a = a.replace(/\s|0x/g, ""), d = a.length, a += "00000000", b = 0; b < a.length; b += 8)c.push(0 ^ parseInt(a.substr(b, 8), 16));
            return sjcl.bitArray.clamp(c, 4 * d)
        }
    }, sjcl.codec.base64 = {
        J: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", fromBits: function (a, b, c) {
            var d = "", e = 0, f = sjcl.codec.base64.J, g = 0, h = sjcl.bitArray.bitLength(a);
            for (c && (f = f.substr(0, 62) + "-_"), c = 0; 6 * d.length < h;)d += f.charAt((g ^ a[c] >>> e) >>> 26), 6 > e ? (g = a[c] << 6 - e, e += 26, c++) : (g <<= 6, e -= 6);
            for (; 3 & d.length && !b;)d += "=";
            return d
        }, toBits: function (a, b) {
            a = a.replace(/\s|=/g, "");
            var d, h, c = [], e = 0, f = sjcl.codec.base64.J, g = 0;
            for (b && (f = f.substr(0, 62) + "-_"), d = 0; d < a.length; d++)h = f.indexOf(a.charAt(d)), 0 > h && q(new sjcl.exception.invalid("this isn't base64!")), 26 < e ? (e -= 26, c.push(g ^ h >>> e), g = h << 32 - e) : (e += 6, g ^= h << 32 - e);
            return 56 & e && c.push(sjcl.bitArray.partial(56 & e, g, 1)), c
        }
    }, sjcl.codec.base64url = {
        fromBits: function (a) {
            return sjcl.codec.base64.fromBits(a, 1, 1)
        }, toBits: function (a) {
            return sjcl.codec.base64.toBits(a, 1)
        }
    }, sjcl.hash.sha256 = function (a) {
        this.b[0] || this.D(), a ? (this.r = a.r.slice(0), this.o = a.o.slice(0), this.h = a.h) : this.reset()
    }, sjcl.hash.sha256.hash = function (a) {
        return (new sjcl.hash.sha256).update(a).finalize()
    }, sjcl.hash.sha256.prototype = {
        blockSize: 512, reset: function () {
            return this.r = this.N.slice(0), this.o = [], this.h = 0, this
        }, update: function (a) {
            "string" == typeof a && (a = sjcl.codec.utf8String.toBits(a));
            var b, c = this.o = sjcl.bitArray.concat(this.o, a);
            for (b = this.h, a = this.h = b + sjcl.bitArray.bitLength(a), b = 512 + b & -512; b <= a; b += 512)z(this, c.splice(0, 16));
            return this
        }, finalize: function () {
            var a, b = this.o, c = this.r, b = sjcl.bitArray.concat(b, [sjcl.bitArray.partial(1, 1)]);
            for (a = b.length + 2; 15 & a; a++)b.push(0);
            for (b.push(Math.floor(this.h / 4294967296)), b.push(0 | this.h); b.length;)z(this, b.splice(0, 16));
            return this.reset(), c
        }, N: [], b: [], D: function () {
            function a(a) {
                return 4294967296 * (a - Math.floor(a)) | 0
            }

            var d, b = 0, c = 2;
            a:for (; 64 > b; c++) {
                for (d = 2; d * d <= c; d++)if (0 === c % d)continue a;
                8 > b && (this.N[b] = a(Math.pow(c, .5))), this.b[b] = a(Math.pow(c, 1 / 3)), b++
            }
        }
    }, sjcl.mode.ccm = {
        name: "ccm", encrypt: function (a, b, c, d, e) {
            var f, g = b.slice(0), h = sjcl.bitArray, i = h.bitLength(c) / 8, j = h.bitLength(g) / 8;
            for (e = e || 64, d = d || [], 7 > i && q(new sjcl.exception.invalid("ccm: iv must be at least 7 bytes")), f = 2; 4 > f && j >>> 8 * f; f++);
            return f < 15 - i && (f = 15 - i), c = h.clamp(c, 8 * (15 - f)), b = sjcl.mode.ccm.L(a, b, c, d, e, f), g = sjcl.mode.ccm.p(a, g, c, b, e, f), h.concat(g.data, g.tag)
        }, decrypt: function (a, b, c, d, e) {
            e = e || 64, d = d || [];
            var f = sjcl.bitArray, g = f.bitLength(c) / 8, h = f.bitLength(b), i = f.clamp(b, h - e), j = f.bitSlice(b, h - e), h = (h - e) / 8;
            for (7 > g && q(new sjcl.exception.invalid("ccm: iv must be at least 7 bytes")), b = 2; 4 > b && h >>> 8 * b; b++);
            return b < 15 - g && (b = 15 - g), c = f.clamp(c, 8 * (15 - b)), i = sjcl.mode.ccm.p(a, i, c, j, e, b), a = sjcl.mode.ccm.L(a, i.data, c, d, e, b), f.equal(i.tag, a) || q(new sjcl.exception.corrupt("ccm: tag doesn't match")), i.data
        }, L: function (a, b, c, d, e, f) {
            var g = [], h = sjcl.bitArray, i = h.l;
            if (e /= 8, (e % 2 || 4 > e || 16 < e) && q(new sjcl.exception.invalid("ccm: invalid tag length")), (4294967295 < d.length || 4294967295 < b.length) && q(new sjcl.exception.bug("ccm: can't deal with 4GiB or more data")), f = [h.partial(8, (d.length ? 64 : 0) | e - 2 << 2 | f - 1)], f = h.concat(f, c), f[3] |= h.bitLength(b) / 8, f = a.encrypt(f), d.length)for (c = h.bitLength(d) / 8, 65279 >= c ? g = [h.partial(16, c)] : 4294967295 >= c && (g = h.concat([h.partial(16, 65534)], [c])), g = h.concat(g, d), d = 0; d < g.length; d += 4)f = a.encrypt(i(f, g.slice(d, d + 4).concat([0, 0, 0])));
            for (d = 0; d < b.length; d += 4)f = a.encrypt(i(f, b.slice(d, d + 4).concat([0, 0, 0])));
            return h.clamp(f, 8 * e)
        }, p: function (a, b, c, d, e, f) {
            var g, h = sjcl.bitArray;
            g = h.l;
            var i = b.length, j = h.bitLength(b);
            if (c = h.concat([h.partial(8, f - 1)], c).concat([0, 0, 0]).slice(0, 4), d = h.bitSlice(g(d, a.encrypt(c)), 0, e), !i)return {
                tag: d,
                data: []
            };
            for (g = 0; g < i; g += 4)c[3]++, e = a.encrypt(c), b[g] ^= e[0], b[g + 1] ^= e[1], b[g + 2] ^= e[2], b[g + 3] ^= e[3];
            return {tag: d, data: h.clamp(b, j)}
        }
    }, sjcl.mode.ocb2 = {
        name: "ocb2", encrypt: function (a, b, c, d, e, f) {
            128 !== sjcl.bitArray.bitLength(c) && q(new sjcl.exception.invalid("ocb iv must be 128 bits"));
            var g, h = sjcl.mode.ocb2.H, i = sjcl.bitArray, j = i.l, k = [0, 0, 0, 0];
            c = h(a.encrypt(c));
            var l, m = [];
            for (d = d || [], e = e || 64, g = 0; g + 4 < b.length; g += 4)l = b.slice(g, g + 4), k = j(k, l), m = m.concat(j(c, a.encrypt(j(c, l)))), c = h(c);
            return l = b.slice(g), b = i.bitLength(l), g = a.encrypt(j(c, [0, 0, 0, b])), l = i.clamp(j(l.concat([0, 0, 0]), g), b), k = j(k, j(l.concat([0, 0, 0]), g)), k = a.encrypt(j(k, j(c, h(c)))), d.length && (k = j(k, f ? d : sjcl.mode.ocb2.pmac(a, d))), m.concat(i.concat(l, i.clamp(k, e)))
        }, decrypt: function (a, b, c, d, e, f) {
            128 !== sjcl.bitArray.bitLength(c) && q(new sjcl.exception.invalid("ocb iv must be 128 bits")), e = e || 64;
            var l, m, g = sjcl.mode.ocb2.H, h = sjcl.bitArray, i = h.l, j = [0, 0, 0, 0], k = g(a.encrypt(c)), n = sjcl.bitArray.bitLength(b) - e, o = [];
            for (d = d || [], c = 0; c + 4 < n / 32; c += 4)l = i(k, a.decrypt(i(k, b.slice(c, c + 4)))), j = i(j, l), o = o.concat(l), k = g(k);
            return m = n - 32 * c, l = a.encrypt(i(k, [0, 0, 0, m])), l = i(l, h.clamp(b.slice(c), m).concat([0, 0, 0])), j = i(j, l), j = a.encrypt(i(j, i(k, g(k)))), d.length && (j = i(j, f ? d : sjcl.mode.ocb2.pmac(a, d))), h.equal(h.clamp(j, e), h.bitSlice(b, n)) || q(new sjcl.exception.corrupt("ocb: tag doesn't match")), o.concat(h.clamp(l, m))
        }, pmac: function (a, b) {
            var c, d = sjcl.mode.ocb2.H, e = sjcl.bitArray, f = e.l, g = [0, 0, 0, 0], h = a.encrypt([0, 0, 0, 0]), h = f(h, d(d(h)));
            for (c = 0; c + 4 < b.length; c += 4)h = d(h), g = f(g, a.encrypt(f(h, b.slice(c, c + 4))));
            return c = b.slice(c), 128 > e.bitLength(c) && (h = f(h, d(h)), c = e.concat(c, [-2147483648, 0, 0, 0])), g = f(g, c), a.encrypt(f(d(f(h, d(h))), g))
        }, H: function (a) {
            return [a[0] << 1 ^ a[1] >>> 31, a[1] << 1 ^ a[2] >>> 31, a[2] << 1 ^ a[3] >>> 31, a[3] << 1 ^ 135 * (a[0] >>> 31)]
        }
    }, sjcl.mode.gcm = {
        name: "gcm", encrypt: function (a, b, c, d, e) {
            var f = b.slice(0);
            return b = sjcl.bitArray, d = d || [], a = sjcl.mode.gcm.p(!0, a, f, d, c, e || 128), b.concat(a.data, a.tag)
        }, decrypt: function (a, b, c, d, e) {
            var f = b.slice(0), g = sjcl.bitArray, h = g.bitLength(f);
            return e = e || 128, d = d || [], e <= h ? (b = g.bitSlice(f, h - e), f = g.bitSlice(f, 0, h - e)) : (b = f, f = []), a = sjcl.mode.gcm.p(u, a, f, d, c, e), g.equal(a.tag, b) || q(new sjcl.exception.corrupt("gcm: tag doesn't match")), a.data
        }, Z: function (a, b) {
            var c, d, e, f, g, h = sjcl.bitArray.l;
            for (e = [0, 0, 0, 0], f = b.slice(0), c = 0; 128 > c; c++) {
                for ((d = 0 !== (a[Math.floor(c / 32)] & 1 << 31 - c % 32)) && (e = h(e, f)), g = 0 !== (1 & f[3]), d = 3; 0 < d; d--)f[d] = f[d] >>> 1 | (1 & f[d - 1]) << 31;
                f[0] >>>= 1, g && (f[0] ^= -520093696)
            }
            return e
        }, g: function (a, b, c) {
            var d, e = c.length;
            for (b = b.slice(0), d = 0; d < e; d += 4)b[0] ^= 4294967295 & c[d], b[1] ^= 4294967295 & c[d + 1], b[2] ^= 4294967295 & c[d + 2], b[3] ^= 4294967295 & c[d + 3], b = sjcl.mode.gcm.Z(b, a);
            return b
        }, p: function (a, b, c, d, e, f) {
            var g, h, i, j, k, l, m, n, o = sjcl.bitArray;
            for (l = c.length, m = o.bitLength(c), n = o.bitLength(d), h = o.bitLength(e), g = b.encrypt([0, 0, 0, 0]), 96 === h ? (e = e.slice(0), e = o.concat(e, [1])) : (e = sjcl.mode.gcm.g(g, [0, 0, 0, 0], e), e = sjcl.mode.gcm.g(g, e, [0, 0, Math.floor(h / 4294967296), 4294967295 & h])), h = sjcl.mode.gcm.g(g, [0, 0, 0, 0], d), k = e.slice(0), d = h.slice(0), a || (d = sjcl.mode.gcm.g(g, h, c)), j = 0; j < l; j += 4)k[3]++, i = b.encrypt(k), c[j] ^= i[0], c[j + 1] ^= i[1], c[j + 2] ^= i[2], c[j + 3] ^= i[3];
            return c = o.clamp(c, m), a && (d = sjcl.mode.gcm.g(g, h, c)), a = [Math.floor(n / 4294967296), 4294967295 & n, Math.floor(m / 4294967296), 4294967295 & m], d = sjcl.mode.gcm.g(g, d, a), i = b.encrypt(e), d[0] ^= i[0], d[1] ^= i[1], d[2] ^= i[2], d[3] ^= i[3], {
                tag: o.bitSlice(d, 0, f),
                data: c
            }
        }
    }, sjcl.misc.hmac = function (a, b) {
        this.M = b = b || sjcl.hash.sha256;
        var d, c = [[], []], e = b.prototype.blockSize / 32;
        for (this.n = [new b, new b], a.length > e && (a = b.hash(a)), d = 0; d < e; d++)c[0][d] = 909522486 ^ a[d], c[1][d] = 1549556828 ^ a[d];
        this.n[0].update(c[0]), this.n[1].update(c[1]), this.G = new b(this.n[0])
    }, sjcl.misc.hmac.prototype.encrypt = sjcl.misc.hmac.prototype.mac = function (a) {
        return this.Q && q(new sjcl.exception.invalid("encrypt on already updated hmac called!")), this.update(a), this.digest(a)
    }, sjcl.misc.hmac.prototype.reset = function () {
        this.G = new this.M(this.n[0]), this.Q = u
    }, sjcl.misc.hmac.prototype.update = function (a) {
        this.Q = !0, this.G.update(a)
    }, sjcl.misc.hmac.prototype.digest = function () {
        var a = this.G.finalize(), a = new this.M(this.n[1]).update(a).finalize();
        return this.reset(), a
    }, sjcl.misc.pbkdf2 = function (a, b, c, d, e) {
        c = c || 1e3, (0 > d || 0 > c) && q(sjcl.exception.invalid("invalid params to pbkdf2")), "string" == typeof a && (a = sjcl.codec.utf8String.toBits(a)), "string" == typeof b && (b = sjcl.codec.utf8String.toBits(b)), e = e || sjcl.misc.hmac, a = new e(a);
        var f, g, h, i, j = [], k = sjcl.bitArray;
        for (i = 1; 32 * j.length < (d || 1); i++) {
            for (e = f = a.encrypt(k.concat(b, [i])), g = 1; g < c; g++)for (f = a.encrypt(f), h = 0; h < f.length; h++)e[h] ^= f[h];
            j = j.concat(e)
        }
        return d && (j = k.clamp(j, d)), j
    }, sjcl.prng = function (a) {
        this.c = [new sjcl.hash.sha256], this.i = [0], this.F = 0, this.s = {}, this.C = 0, this.K = {}, this.O = this.d = this.j = this.W = 0, this.b = [0, 0, 0, 0, 0, 0, 0, 0], this.f = [0, 0, 0, 0], this.A = t, this.B = a, this.q = u, this.w = {
            progress: {},
            seeded: {}
        }, this.m = this.V = 0, this.t = 1, this.u = 2, this.S = 65536, this.I = [0, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024], this.T = 3e4, this.R = 80
    }, sjcl.prng.prototype = {
        randomWords: function (a, b) {
            var d, c = [];
            d = this.isReady(b);
            var e;
            if (d === this.m && q(new sjcl.exception.notReady("generator isn't seeded")), d & this.u) {
                d = !(d & this.t), e = [];
                var g, f = 0;
                for (this.O = e[0] = (new Date).valueOf() + this.T, g = 0; 16 > g; g++)e.push(4294967296 * Math.random() | 0);
                for (g = 0; g < this.c.length && (e = e.concat(this.c[g].finalize()), f += this.i[g], this.i[g] = 0, !(!d && this.F & 1 << g)); g++);
                for (this.F >= 1 << this.c.length && (this.c.push(new sjcl.hash.sha256), this.i.push(0)), this.d -= f, f > this.j && (this.j = f), this.F++, this.b = sjcl.hash.sha256.hash(this.b.concat(e)), this.A = new sjcl.cipher.aes(this.b), d = 0; 4 > d && (this.f[d] = this.f[d] + 1 | 0, !this.f[d]); d++);
            }
            for (d = 0; d < a; d += 4)0 === (d + 1) % this.S && A(this),
                e = B(this), c.push(e[0], e[1], e[2], e[3]);
            return A(this), c.slice(0, a)
        }, setDefaultParanoia: function (a, b) {
            0 === a && "Setting paranoia=0 will ruin your security; use it only for testing" !== b && q("Setting paranoia=0 will ruin your security; use it only for testing"), this.B = a
        }, addEntropy: function (a, b, c) {
            c = c || "user";
            var d, e, f = (new Date).valueOf(), g = this.s[c], h = this.isReady(), i = 0;
            switch (d = this.K[c], d === t && (d = this.K[c] = this.W++), g === t && (g = this.s[c] = 0), this.s[c] = (this.s[c] + 1) % this.c.length, typeof a) {
                case"number":
                    b === t && (b = 1), this.c[g].update([d, this.C++, 1, b, f, 1, 0 | a]);
                    break;
                case"object":
                    if (c = Object.prototype.toString.call(a), "[object Uint32Array]" === c) {
                        for (e = [], c = 0; c < a.length; c++)e.push(a[c]);
                        a = e
                    } else for ("[object Array]" !== c && (i = 1), c = 0; c < a.length && !i; c++)"number" != typeof a[c] && (i = 1);
                    if (!i) {
                        if (b === t)for (c = b = 0; c < a.length; c++)for (e = a[c]; 0 < e;)b++, e >>>= 1;
                        this.c[g].update([d, this.C++, 2, b, f, a.length].concat(a))
                    }
                    break;
                case"string":
                    b === t && (b = a.length), this.c[g].update([d, this.C++, 3, b, f, a.length]), this.c[g].update(a);
                    break;
                default:
                    i = 1
            }
            i && q(new sjcl.exception.bug("random: addEntropy only supports number, array of numbers or string")), this.i[g] += b, this.d += b, h === this.m && (this.isReady() !== this.m && C("seeded", Math.max(this.j, this.d)), C("progress", this.getProgress()))
        }, isReady: function (a) {
            return a = this.I[a !== t ? a : this.B], this.j && this.j >= a ? this.i[0] > this.R && (new Date).valueOf() > this.O ? this.u | this.t : this.t : this.d >= a ? this.u | this.m : this.m
        }, getProgress: function (a) {
            return a = this.I[a ? a : this.B], this.j >= a ? 1 : this.d > a ? 1 : this.d / a
        }, startCollectors: function () {
            this.q || (this.a = {
                loadTimeCollector: D(this, this.aa),
                mouseCollector: D(this, this.ba),
                keyboardCollector: D(this, this.$),
                accelerometerCollector: D(this, this.U)
            }, window.addEventListener ? (window.addEventListener("load", this.a.loadTimeCollector, u), window.addEventListener("mousemove", this.a.mouseCollector, u), window.addEventListener("keypress", this.a.keyboardCollector, u), window.addEventListener("devicemotion", this.a.accelerometerCollector, u)) : document.attachEvent ? (document.attachEvent("onload", this.a.loadTimeCollector), document.attachEvent("onmousemove", this.a.mouseCollector), document.attachEvent("keypress", this.a.keyboardCollector)) : q(new sjcl.exception.bug("can't attach event")), this.q = !0)
        }, stopCollectors: function () {
            this.q && (window.removeEventListener ? (window.removeEventListener("load", this.a.loadTimeCollector, u), window.removeEventListener("mousemove", this.a.mouseCollector, u), window.removeEventListener("keypress", this.a.keyboardCollector, u), window.removeEventListener("devicemotion", this.a.accelerometerCollector, u)) : document.detachEvent && (document.detachEvent("onload", this.a.loadTimeCollector), document.detachEvent("onmousemove", this.a.mouseCollector), document.detachEvent("keypress", this.a.keyboardCollector)), this.q = u)
        }, addEventListener: function (a, b) {
            this.w[a][this.V++] = b
        }, removeEventListener: function (a, b) {
            var c, d, e = this.w[a], f = [];
            for (d in e)e.hasOwnProperty(d) && e[d] === b && f.push(d);
            for (c = 0; c < f.length; c++)d = f[c], delete e[d]
        }, $: function () {
            E(1)
        }, ba: function (a) {
            var b, c;
            try {
                b = a.x || a.clientX || a.offsetX || 0, c = a.y || a.clientY || a.offsetY || 0
            } catch (a) {
                c = b = 0
            }
            0 != b && 0 != c && sjcl.random.addEntropy([b, c], 2, "mouse"), E(0)
        }, aa: function () {
            E(2)
        }, U: function (a) {
            if (a = a.accelerationIncludingGravity.x || a.accelerationIncludingGravity.y || a.accelerationIncludingGravity.z, window.orientation) {
                var b = window.orientation;
                "number" == typeof b && sjcl.random.addEntropy(b, 1, "accelerometer")
            }
            a && sjcl.random.addEntropy(a, 2, "accelerometer"), E(0)
        }
    }, sjcl.random = new sjcl.prng(6);
    a:try {
        var F, G, H, I;
        if (I = "undefined" != typeof module) {
            var J;
            if (J = module.exports) {
                var K;
                try {
                    K = require("crypto")
                } catch (a) {
                    K = null
                }
                J = (G = K) && G.randomBytes
            }
            I = J
        }
        if (I) F = G.randomBytes(128), F = new Uint32Array(new Uint8Array(F).buffer), sjcl.random.addEntropy(F, 1024, "crypto['randomBytes']"); else if ("undefined" != typeof window && "undefined" != typeof Uint32Array) {
            if (H = new Uint32Array(32), window.crypto && window.crypto.getRandomValues) window.crypto.getRandomValues(H); else {
                if (!window.msCrypto || !window.msCrypto.getRandomValues)break a;
                window.msCrypto.getRandomValues(H)
            }
            sjcl.random.addEntropy(H, 1024, "crypto['getRandomValues']")
        }
    } catch (a) {
        "undefined" != typeof window && window.console && (console.log("There was an error collecting entropy from the browser:"), console.log(a))
    }
    sjcl.json = {
        defaults: {v: 1, iter: 1e3, ks: 128, ts: 64, mode: "ccm", adata: "", cipher: "aes"}, Y: function (a, b, c, d) {
            c = c || {}, d = d || {};
            var g, e = sjcl.json, f = e.e({iv: sjcl.random.randomWords(4, 0)}, e.defaults);
            return e.e(f, c), c = f.adata, "string" == typeof f.salt && (f.salt = sjcl.codec.base64.toBits(f.salt)), "string" == typeof f.iv && (f.iv = sjcl.codec.base64.toBits(f.iv)), (!sjcl.mode[f.mode] || !sjcl.cipher[f.cipher] || "string" == typeof a && 100 >= f.iter || 64 !== f.ts && 96 !== f.ts && 128 !== f.ts || 128 !== f.ks && 192 !== f.ks && 256 !== f.ks || 2 > f.iv.length || 4 < f.iv.length) && q(new sjcl.exception.invalid("json encrypt: invalid parameters")), "string" == typeof a ? (g = sjcl.misc.cachedPbkdf2(a, f), a = g.key.slice(0, f.ks / 32), f.salt = g.salt) : sjcl.ecc && a instanceof sjcl.ecc.elGamal.publicKey && (g = a.kem(), f.kemtag = g.tag, a = g.key.slice(0, f.ks / 32)), "string" == typeof b && (b = sjcl.codec.utf8String.toBits(b)), "string" == typeof c && (c = sjcl.codec.utf8String.toBits(c)), g = new sjcl.cipher[f.cipher](a), e.e(d, f), d.key = a, f.ct = sjcl.mode[f.mode].encrypt(g, b, f.iv, c, f.ts), f
        }, encrypt: function (a, b, c, d) {
            var e = sjcl.json, f = e.Y.apply(e, arguments);
            return e.encode(f)
        }, X: function (a, b, c, d) {
            c = c || {}, d = d || {};
            var e = sjcl.json;
            b = e.e(e.e(e.e({}, e.defaults), b), c, !0);
            var f, g;
            return f = b.adata, "string" == typeof b.salt && (b.salt = sjcl.codec.base64.toBits(b.salt)), "string" == typeof b.iv && (b.iv = sjcl.codec.base64.toBits(b.iv)), (!sjcl.mode[b.mode] || !sjcl.cipher[b.cipher] || "string" == typeof a && 100 >= b.iter || 64 !== b.ts && 96 !== b.ts && 128 !== b.ts || 128 !== b.ks && 192 !== b.ks && 256 !== b.ks || !b.iv || 2 > b.iv.length || 4 < b.iv.length) && q(new sjcl.exception.invalid("json decrypt: invalid parameters")), "string" == typeof a ? (g = sjcl.misc.cachedPbkdf2(a, b), a = g.key.slice(0, b.ks / 32), b.salt = g.salt) : sjcl.ecc && a instanceof sjcl.ecc.elGamal.secretKey && (a = a.unkem(sjcl.codec.base64.toBits(b.kemtag)).slice(0, b.ks / 32)), "string" == typeof f && (f = sjcl.codec.utf8String.toBits(f)), g = new sjcl.cipher[b.cipher](a), f = sjcl.mode[b.mode].decrypt(g, b.ct, b.iv, f, b.ts), e.e(d, b), d.key = a, 1 === c.raw ? f : sjcl.codec.utf8String.fromBits(f)
        }, decrypt: function (a, b, c, d) {
            var e = sjcl.json;
            return e.X(a, e.decode(b), c, d)
        }, encode: function (a) {
            var b, c = "{", d = "";
            for (b in a)if (a.hasOwnProperty(b))switch (b.match(/^[a-z0-9]+$/i) || q(new sjcl.exception.invalid("json encode: invalid property name")), c += d + '"' + b + '":', d = ",", typeof a[b]) {
                case"number":
                case"boolean":
                    c += a[b];
                    break;
                case"string":
                    c += '"' + escape(a[b]) + '"';
                    break;
                case"object":
                    c += '"' + sjcl.codec.base64.fromBits(a[b], 0) + '"';
                    break;
                default:
                    q(new sjcl.exception.bug("json encode: unsupported type"))
            }
            return c + "}"
        }, decode: function (a) {
            a = a.replace(/\s/g, ""), a.match(/^\{.*\}$/) || q(new sjcl.exception.invalid("json decode: this isn't json!")), a = a.replace(/^\{|\}$/g, "").split(/,/);
            var c, d, b = {};
            for (c = 0; c < a.length; c++)(d = a[c].match(/^(?:(["']?)([a-z][a-z0-9]*)\1):(?:(\d+)|"([a-z0-9+\/%*_.@=\-]*)")$/i)) || q(new sjcl.exception.invalid("json decode: this isn't json!")), b[d[2]] = d[3] ? parseInt(d[3], 10) : d[2].match(/^(ct|salt|iv)$/) ? sjcl.codec.base64.toBits(d[4]) : unescape(d[4]);
            return b
        }, e: function (a, b, c) {
            if (a === t && (a = {}), b === t)return a;
            for (var d in b)b.hasOwnProperty(d) && (c && a[d] !== t && a[d] !== b[d] && q(new sjcl.exception.invalid("required parameter overridden")), a[d] = b[d]);
            return a
        }, ea: function (a, b) {
            var d, c = {};
            for (d in a)a.hasOwnProperty(d) && a[d] !== b[d] && (c[d] = a[d]);
            return c
        }, da: function (a, b) {
            var d, c = {};
            for (d = 0; d < b.length; d++)a[b[d]] !== t && (c[b[d]] = a[b[d]]);
            return c
        }
    }, sjcl.encrypt = sjcl.json.encrypt, sjcl.decrypt = sjcl.json.decrypt, sjcl.misc.ca = {}, sjcl.misc.cachedPbkdf2 = function (a, b) {
        var d, c = sjcl.misc.ca;
        return b = b || {}, d = b.iter || 1e3, c = c[a] = c[a] || {}, d = c[d] = c[d] || {firstSalt: b.salt && b.salt.length ? b.salt.slice(0) : sjcl.random.randomWords(2, 0)}, c = b.salt === t ? d.firstSalt : b.salt, d[c] = d[c] || sjcl.misc.pbkdf2(a, c, b.iter), {
            key: d[c].slice(0),
            salt: c.slice(0)
        }
    };
    var drwp = window.drwp = window.drwp || {}, encrypt = drwp.encrypt = drwp.encrypt || {
            createPaymentForm: function (a, b) {
                return new PaymentForm(a, b)
            }
        };
    encrypt.errors = encrypt.errors || {};
    var PaymentForm = encrypt.PaymentForm = function (a, b) {
        try {
            sjcl.random.startCollectors()
        } catch (a) {
            console.log("cannot start sjcl random")
        }
        this.key = a, this.drCompanyId = b, this.validity = {}
    };
    PaymentForm.prototype = {
        constructor: PaymentForm, createRSAKey: function () {
            var a = this.key.split("|");
            if (2 != a.length)throw"Malformed public key";
            var b = a[0], c = a[1], d = new RSAKey;
            return d.setPublic(c, b), d
        }, createAESKey: function () {
            return new AESKey
        }, encryptPaymentForm: function (a) {
            var b, c, d, e = {};
            b = this.createRSAKey(), c = this.createAESKey(), e = c.encrypt(a), keybytes = sjcl.codec.base64url.fromBits(c.key), d = b.encrypt(keybytes);
            var f = "DRWP_1.0", g = [f, "#", this.drCompanyId, "#", d, "#", e.iv, "#", e.cipher].join("");
            return g
        }
    };
    var AESKey = function () {
    }, cipherIVObject = {cipher: null, iv: null};
    AESKey.prototype = {
        constructor: AESKey, key: sjcl.random.randomWords(8, 0), encrypt: function (a) {
            return this.encryptWithInitializationVector(a, sjcl.random.randomWords(4, 0))
        }, encryptWithInitializationVector: function (a, b) {
            var c, d, e;
            return c = new sjcl.cipher.aes(this.key), d = sjcl.codec.utf8String.toBits(a), e = sjcl.mode.gcm.encrypt(c, d, b), cipherIVObject.cipher = sjcl.codec.base64url.fromBits(e), cipherIVObject.iv = sjcl.codec.base64url.fromBits(b), cipherIVObject
        }
    }
}();