def call(String repoUrl, String credentialsId) {
    // Load credentials securely from Jenkins
    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        usernameVariable: 'SWR_USER',
        passwordVariable: 'SWR_PASSWORD'
    )]) {
        // Execute skopeo command and parse output with jq
        def latestTag = sh(script: """
            skopeo list-tags \
                --creds="${SWR_USER}:${SWR_PASSWORD}" \
                docker://${repoUrl} \
                | jq -r '.Tags | map(select(. != "latest")) | sort | last'
        """, returnStdout: true).trim()

        echo "Latest tag in SWR: ${latestTag}"
        return latestTag
    }
}
