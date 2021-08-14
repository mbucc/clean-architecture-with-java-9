
jars: all
	mkdir -p jars
	jar --create --file ./jars/com.example.main@1.jar --module-version 1 --main-class com/example/main/Server -C mods/com.example.main .
	jar --create --file ./jars/com.example.siteinfo.ui@1.jar --module-version 1 -C mods/com.example.siteinfo.ui .
	jar --create --file ./jars/com.example.siteinfo.db@1.jar --module-version 1 -C mods/com.example.siteinfo.db .
	jar --create --file ./jars/com.example.siteinfo.core@1.jar --module-version 1 -C mods/com.example.siteinfo.core .
	jar --create --file ./jars/com.example.ui@1.jar --module-version 1 -C mods/com.example.ui .

.PHONY: all
all:
	javac -d mods --module-path lib --module-source-path ./src $$(find src -name '*.java')


tgz: clean.tgz
clean.tgz: jars
	tar czvf $@ ./jars ./lib start.sh stop.sh

.PHONY: clean
clean:
	rm -rf mods
	rm -f clean.tgz
	rm -rf jars

