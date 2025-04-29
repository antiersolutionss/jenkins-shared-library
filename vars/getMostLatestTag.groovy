def call(String repoUrl, String credentialsId) {
    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        usernameVariable: 'SWR_USER',
        passwordVariable: 'SWR_PASSWORD'
    )]) {
        try {
            def tags = sh(script: """
                skopeo list-tags \
                    --creds="${SWR_USER}:${SWR_PASSWORD}" \
                    docker://${repoUrl} \
                    | jq -r '.Tags[] | select(. != "latest")'
            """, returnStdout: true).trim()
            
            def latestTag = tags.split('\n').sort().last()
            return latestTag ?: null
        } catch (Exception e) {
            echo "Failed to fetch tags: ${e}"
            return null
        }
    }
}
