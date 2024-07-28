package com.microsoft.build;

import java.util.Map;

/**
 * The type Lookup context.
 */
public class LookupContext {

  private final Map<String, String> globalLookup;
  private final Map<String, String> localLookup;

    /**
     * Instantiates a new Lookup context.
     *
     * @param globalLookup the global lookup
     * @param localLookup  the local lookup
     */
    public LookupContext(Map<String, String> globalLookup, Map<String, String> localLookup) {
    this.globalLookup = globalLookup;
    this.localLookup = localLookup;
  }

    /**
     * Resolve string.
     *
     * @param key the key
     * @return the string
     */
    public String resolve(String key) {
    if (localLookup.containsKey(key)) {
      return localLookup.get(key);
    }
    return globalLookup.get(key);
  }

    /**
     * Gets owner uid.
     *
     * @return the owner uid
     */
    public String getOwnerUid() {
    return localLookup.keySet().iterator().next();
  }

    /**
     * Contains key boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean containsKey(String key) {
    return localLookup.containsKey(key) || globalLookup.containsKey(key);
  }
}
