def call(Map args) {
    if (!args.scriptPath || !args.serverIp) {
        error("Missing required parameters: scriptPath and serverIp must be specified")
    }
    
    def credentialsId = args.credentialsId
    
    withEnv(["TARGET_IP=${args.serverIp}", "SCRIPT_PATH=${args.scriptPath}"]) {
        if (credentialsId) {
            echo "🚀 SSH Deploying to staging using sshagent credential '${credentialsId}'..."
            sshagent(credentials: [credentialsId]) {
                sh 'ssh -vvv -o StrictHostKeyChecking=no root@$TARGET_IP "$SCRIPT_PATH"'
            }
        } else {
            echo "🚀 SSH Deploying to staging using default system SSH keys..."
            sh 'ssh -vvv -o StrictHostKeyChecking=no root@$TARGET_IP "$SCRIPT_PATH"'
        }
    }
}
