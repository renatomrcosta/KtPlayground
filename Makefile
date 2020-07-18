.PHONY: compile-thrift
compile-thrift:
	mkdir -p src/main/
	docker run -v "$(PWD):/data" jaegertracing/thrift:0.13  thrift -o /data/src/main/ --gen java:private-members /data/src/main/kotlin/coThrift/service.thrift
