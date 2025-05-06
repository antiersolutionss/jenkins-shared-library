def call(String credentialsId) {
    withCredentials([file(credentialsId: credentialsId, variable: 'ENV_FILE')]) {
        sh '''
            #!/bin/bash
            set -e

            # Copy the env file
            cp "$ENV_FILE" .env

            # Clean up and export valid lines
            grep -v '^#' .env | grep '=' | sed 's/^/export /' > env_export.sh

            chmod +x env_export.sh

            echo "Generated env_export.sh:"
            cat env_export.sh
        '''
    }
}
