def call(String credentialsId) {
    withCredentials([file(credentialsId: credentialsId, variable: 'ENV_FILE')]) {
        sh '''
            # Copy the secret file
            sudo cp "$ENV_FILE" .env

            # Transform it into a shell-exportable script
           sudo  awk -F ': ' '{gsub(/'\''/, "", $2); print "export " $1 "=" $2}' .env > env_export.sh

            # Make it executable
            sudo chmod +x env_export.sh

            # Optional: show the generated file
            echo "Transformed environment file:"
            cat env_export.sh
        '''
    }
}
