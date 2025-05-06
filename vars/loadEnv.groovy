def call(String credentialsId) {
    withCredentials([file(credentialsId: credentialsId, variable: 'ENV_FILE')]) {
        // Read the .env file into a Map
        def props = readProperties file: ENV_FILE

        // Inject all keys into the Jenkins environment
        props.each { key, value ->
            env[key] = value
        }

        echo "✅ Loaded environment variables from ${credentialsId}"
    }
}
