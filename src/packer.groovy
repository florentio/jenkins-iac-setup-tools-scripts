def call(String version = "latest", String directory = "${env.WORKSPACE}") {
   try {
        sh("mkdir -p ${directory}")
        def releaseUrl = sh(returnStdout: true, script : "curl -sS https://api.releases.hashicorp.com/v1/releases/packer/${version} | jq '.builds[] | select(.os == \"linux\" and .arch == \"amd64\")' | jq -r '.url'")
        sh("wget -q ${releaseUrl}")
        String fileName = releaseUrl.split("/").last().trim()
        sh("unzip ${fileName} -d ${directory}")
        sh("${directory}/packer --version")
    } catch (Exception e) {
        println(e.toString())
        error("Could not install packer")
    }
}
