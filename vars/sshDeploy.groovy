def call(Map args) {
    if (!args.scriptPath || !args.serverIp) {
        error("Missing required parameters: scriptPath and serverIp must be specified")
    }
    
    echo "🚀 SSH Deploying to ${args.serverIp} using script ${args.scriptPath}..."
    sh "ssh -o StrictHostKeyChecking=no root@${args.serverIp} '${args.scriptPath}'"
}
