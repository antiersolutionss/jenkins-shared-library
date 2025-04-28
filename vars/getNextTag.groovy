def call(String currentTag) {
    // currentTag format: v1.0.1
    def parts = currentTag.replace('v', '').split('\\.') // ["1", "0", "1"]

    int major = parts[0].toInteger()
    int minor = parts[1].toInteger()
    int patch = parts[2].toInteger()

    patch++ // Increment patch version

    if (patch > 10) {
        patch = 1  // Reset patch version
        minor++  // Increment minor version
    }

    if (minor > 10) {
        minor = 0  // Reset minor version
        major++  // Increment major version
    }

    return "v${major}.${minor}.${patch}"
}
