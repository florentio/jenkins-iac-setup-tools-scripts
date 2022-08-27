def call(String version = "0.16.0", String directory = "${env.WORKSPACE}") {
    try {
        sh("mkdir -p ${directory}/")
        sh("curl -sSLo ./terraform-docs.tar.gz https://github.com/terraform-docs/terraform-docs/releases/download/v${version}/terraform-docs-v${version}-Linux-amd64.tar.gz")
        sh("tar -xzf terraform-docs.tar.gz")
        sh("chmod +x terraform-docs")
        sh("${directory}/terraform-docs --version")
    } catch (Exception e) {
        println(e.toString())
        error("Could not install terraform-docs")
    }
}
