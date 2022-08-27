.ONESHELL:

RELEASE_NAME = jenkins-shared-libraries

# Get current directory
current_dir = $(shell pwd)

build-container:
	@docker-compose pull ${RELEASE_NAME}
	@docker-compose build ${RELEASE_NAME}

run: build-container
	@docker-compose run --rm ${RELEASE_NAME}

exec: build-container
	@docker-compose run -d --name ${RELEASE_NAME} --rm ${RELEASE_NAME} "sh"

shell:
	docker exec -it $(RELEASE_NAME) sh

test:
	@./gradlew test --info
