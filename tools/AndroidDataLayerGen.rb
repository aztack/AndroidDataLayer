require 'json'
require 'erubis'
require 'ostruct'

class ::String
	def underscore
		gsub(/::/, '/').
		gsub(/([A-Z]+)([A-Z][a-z])/,'\1_\2').
		gsub(/([a-z\d])([A-Z])/,'\1_\2').
		tr("- ", "_")
	end
end

#
# An Android Data Layer Generation Tool
#
module AndroidDataLayerGen
	module Renderable
		def render(tpl)
			Erubis::Eruby.new(tpl).result binding
		end
	end

	#
	# `Item` generate java file for classes which extends ItemBase(.java)
	#
	# An ItemBase descendant represents a structural
	# JSON data received from server
	# The structure of these classes are defined in protocols.json
	#
	class Item < OpenStruct
		include Renderable
		def initialize(table, name, package)
			super(table)
			@name, @package = name, package
		end
		def name;@name;end
		def pkg;@package;end

		def each(&block)
			self.marshal_dump.each &block
		end
	end

	#
	# A Protocol define a interface provided by server
	# http://server.com/api/getUser => a protocol named getUser
	#
	class Protocol < OpenStruct
		def initialize(table, name)
			super(table)
			@name = name
			@inputs = {}
			table['inputs'].each do |name,type_info|
				@inputs[name] = OpenStruct.new(type_info)
			end
		end
		attr_reader :name, :inputs
		
		public
		def java_signature
			@inputs.inject([]){|s,(name,info)|s <<"#{info.type} #{name}";s}.join ","
		end

		def input_names
			@inputs.inject([]){|s,(name,_)|s << name;s}.join ","
		end

		@@TypeStringFormat = {
			"int"=>"%d",
			"integer" => "%d",
			"string" => "%s"
		}

		def fmt_string
			@inputs.inject([]) do |s, (name,info)|
				fmt = @@TypeStringFormat[info.type.downcase]
				s << "#{name}=#{fmt}"; s
			end.join "&"
		end
	end

	#
	# A Protocols represents a protocols.json file
	# and generate a DataLayerGenerated.java from this json file
	# In your project you extends this DataLayerGenereated, add
	# more specific functionality
	#
	class Protocols
		include Renderable
		def initialize(name,pkg, tpkg,protocols,base)
			@name = name
			@pkg = pkg
			@type_pkg = tpkg
			@protocols = protocols
			@base = base
		end
		attr_reader :name, :pkg, :type_pkg, :protocols,:base
	end
end

if $0 == __FILE__
	include AndroidDataLayerGen
	if ARGV.size != 2
		script = File.basename($0)
		$stdout.puts "USAGE: #{script} PROTOCO.JSON SRC_ROOT"
		$stdout.puts "example: #{script} protocol.json ../src"
		exit
	end

	require 'fileutils'	
	PWD = File.dirname(File.expand_path(__FILE__))

	ItemTemplate      = File.read File.expand_path("Item.template.java",PWD)
	DataLayerTemplate = File.read File.expand_path("DataLayer.template.java",PWD)

	protocol_json_path = File.join(PWD,ARGV[0])
	SRC_ROOT = ARGV[1]
	protocol_json = File.read(protocol_json_path)
	json = OpenStruct.new(JSON.parse(protocol_json))

	@base = json.base
	@pkg = json.package
	@type_pkg = json.typePackage

	PkgDir = "#{SRC_ROOT}/#{@pkg.gsub('.','/')}"
	FileUtils.makedirs(PkgDir)
	TypeDir = "#{SRC_ROOT}/#{@type_pkg.gsub('.','/')}"
	FileUtils.makedirs(TypeDir)
	
	@types = {}
	@protocols = {}
	
	json.types.each do |name,struct|
		@types[name] = Item.new(struct, name, @type_pkg)
	end

	json.protocols.each do |struct|
		name = struct["name"]
		o = Protocol.new(struct,name)
		@protocols[name] = o
	end

	#####
	DataLayerName = "DataLayerGenerated"
	protocols = Protocols.new(DataLayerName,@pkg, @type_pkg,@protocols,@base)
	File.open("#{PkgDir}/#{DataLayerName}.java","w") do |f|
		f.puts protocols.render(DataLayerTemplate)
	end
	@types.each do |(name,type)|
		File.open("#{TypeDir}/#{name}.java","w"){|f|f.puts type.render(ItemTemplate)}
	end
end

