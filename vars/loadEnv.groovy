def call(String credentialsId) {
    withCredentials([file(credentialsId: credentialsId, variable: 'ENV_FILE')]) {
        // Copy the secret file into the workspace
         sh "sudo cp \"$ENV_FILE\" .env"

        // Load environment variables from .env file
        def envFile = readFile('.env')
        def envVars = []
        envFile.split('\n').each { line ->
            def keyValue = line.split('=')
            if (keyValue.size() == 2) {
                envVars.add("${keyValue[0].trim()}=${keyValue[1].trim()}")
            }
        }

        // Export the variables for use in following pipeline steps
        envVars.each { envVar ->
            def (key, value) = envVar.tokenize('=')
            env[key] = value
        }

        echo "Environment variables loaded from ${credentialsId}."
         // List all environment variables
        sh "printenv"
    }
}
