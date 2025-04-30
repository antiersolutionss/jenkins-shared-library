def call(String branch, String credentialsId, String repoUrl) {
    git branch: branch, credentialsId: credentialsId, url: repoUrl
    echo "Successfully logged in and cloned the Git repository"
}
