// vars/getMostLatestTag.groovy
def call(Map args) {
    // Validate required parameters
    if (!args.repoUrl || !args.credentialsId) {
        error("Missing required parameters: repoUrl and credentialsId must be specified")
    }

    withCredentials([usernamePassword(
        credentialsId: args.credentialsId,
        usernameVariable: 'SWR_USER',
        passwordVariable: 'SWR_PASSWORD'
    )]) {
        try {
            def tags = sh(script: """
                skopeo list-tags \
                    --creds="\${SWR_USER}:\${SWR_PASSWORD}" \
                    docker://${args.repoUrl} \
                    | jq -r '.Tags[] | select(. != "latest")' \
                    | sort | tail -1
            """, returnStdout: true).trim()
            
            return tags ?: null
        } catch (Exception e) {
            echo "Warning: Failed to fetch tags from ${args.repoUrl}: ${e}"
            return null
        }
    }
}
