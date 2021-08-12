// Quoting from https://stackoverflow.com/a/49544403/1789168:
//
//	As per JEP 261 the --module-source-path option (for compilation in
//	"multi-module mode") must point to a directory that holds one subdirectory
//	for each contained module, where the directory name must equal the
//	module name.
//
// Per https://www.logicbig.com/tutorials/core-java-tutorial/modules/modes.html,
// when javac is in multi-module mode, you can compile all modules with one command.
//
// As module names must be globally unique, the recommended practice is to
// use the reverse DNS convention (the same one used for packages).
//
module com.example.siteinfo.core {
	exports com.example.siteinfo.core.entities;
	exports com.example.siteinfo.core.usecases;
	exports com.example.siteinfo.core.ports;
	provides com.example.siteinfo.core.usecases.GetSiteInfo
		with com.example.siteinfo.core.services.GetSiteInfoService;
	uses com.example.siteinfo.core.ports.FindSiteInfo;
}
