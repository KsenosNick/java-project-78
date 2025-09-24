install:
	make -C app install

run:
	make -C app run

build:
	make -C app build

report:
	make -C app report

test:
	make -C app test

lint:
	make -C app lint

.PHONY: build
