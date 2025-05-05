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
            // Fetch tags from the repository
            def tags = sh(script: """
                skopeo list-tags \
                    --creds="\${SWR_USER}:\${SWR_PASSWORD}" \
                    docker://${args.repoUrl} \
                    | jq -r '.Tags[] | select(. != "latest")' \
                    | sort | tail -1
            """, returnStdout: true).trim()

            // If there are no tags, return "1.0.0"
            if (!tags) {
                echo "No tags found, returning 1.0.0"
                return "1.0.0"
            }

            // Check if the latest tag matches semantic versioning format
            // Regular expression for x.y.z format (e.g., 1.2.3)
            def semanticVersionPattern = /^\d+\.\d+\.\d+$/

            if (tags ==~ semanticVersionPattern) {
                return tags
            } else {
                echo "Latest tag is not in semantic version format, returning 1.0.0"
                return "1.0.0"
            }
        } catch (Exception e) {
            echo "Warning: Failed to fetch tags from ${args.repoUrl}: ${e}"
            return "1.0.0" // Fallback to 1.0.0 in case of error
        }
    }
}
