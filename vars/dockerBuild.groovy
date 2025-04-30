def call() {
    echo "Building Docker image: env.NEXT_TAG "
    sh "ls"
    sh "docker build -t ${env.REPO_URL}:${env.NEXT_TAG} ."
    echo "IMAGE BUILT SUCCESSFULLY!!"
}
