package yardspoon.booklouse;

enum LookupResult {

    FOUND_PREFIX(true, true),
    FOUND_NOT_PREFIX(true, false),
    NOT_FOUND_PREFIX(false, true),
    NOT_FOUND_NOT_PREFIX(false, false);
    
    final boolean wasFound;
    final boolean isPrefix;

    LookupResult(boolean wasFound, boolean isPrefix) {
        this.wasFound = wasFound;
        this.isPrefix = isPrefix;
    }
}