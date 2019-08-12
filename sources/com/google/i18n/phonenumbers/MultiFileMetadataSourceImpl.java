package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadata;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

final class MultiFileMetadataSourceImpl implements MetadataSource {
    private static final Logger logger = Logger.getLogger(MultiFileMetadataSourceImpl.class.getName());
    private final String filePrefix;
    private final ConcurrentHashMap<String, PhoneMetadata> geographicalRegions;
    private final MetadataLoader metadataLoader;
    private final ConcurrentHashMap<Integer, PhoneMetadata> nonGeographicalRegions;

    MultiFileMetadataSourceImpl(String filePrefix2, MetadataLoader metadataLoader2) {
        this.geographicalRegions = new ConcurrentHashMap<>();
        this.nonGeographicalRegions = new ConcurrentHashMap<>();
        this.filePrefix = filePrefix2;
        this.metadataLoader = metadataLoader2;
    }

    public MultiFileMetadataSourceImpl(MetadataLoader metadataLoader2) {
        this("/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto", metadataLoader2);
    }

    public PhoneMetadata getMetadataForRegion(String regionCode) {
        PhoneMetadata metadata = (PhoneMetadata) this.geographicalRegions.get(regionCode);
        return metadata != null ? metadata : loadMetadataFromFile(regionCode, this.geographicalRegions, this.filePrefix, this.metadataLoader);
    }

    public PhoneMetadata getMetadataForNonGeographicalRegion(int countryCallingCode) {
        PhoneMetadata metadata = (PhoneMetadata) this.nonGeographicalRegions.get(Integer.valueOf(countryCallingCode));
        if (metadata != null) {
            return metadata;
        }
        if (isNonGeographical(countryCallingCode)) {
            return loadMetadataFromFile(Integer.valueOf(countryCallingCode), this.nonGeographicalRegions, this.filePrefix, this.metadataLoader);
        }
        return null;
    }

    private boolean isNonGeographical(int countryCallingCode) {
        List<String> regionCodes = (List) CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap().get(Integer.valueOf(countryCallingCode));
        if (regionCodes.size() != 1 || !"001".equals(regionCodes.get(0))) {
            return false;
        }
        return true;
    }

    static <T> PhoneMetadata loadMetadataFromFile(T key, ConcurrentHashMap<T, PhoneMetadata> map, String filePrefix2, MetadataLoader metadataLoader2) {
        String fileName = filePrefix2 + "_" + key;
        InputStream source = metadataLoader2.loadMetadata(fileName);
        if (source == null) {
            throw new IllegalStateException("missing metadata: " + fileName);
        }
        List<PhoneMetadata> metadataList = MetadataManager.loadMetadataAndCloseInput(source).getMetadataList();
        if (metadataList.isEmpty()) {
            throw new IllegalStateException("empty metadata: " + fileName);
        }
        if (metadataList.size() > 1) {
            logger.log(Level.WARNING, "invalid metadata (too many entries): " + fileName);
        }
        PhoneMetadata metadata = (PhoneMetadata) metadataList.get(0);
        PhoneMetadata oldValue = (PhoneMetadata) map.putIfAbsent(key, metadata);
        return oldValue != null ? oldValue : metadata;
    }
}
