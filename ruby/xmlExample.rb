#!/usr/bin/ruby
#
require "rexml/document"

file = File.new("sample.xml")
doc = REXML::Document.new file 
puts doc.to_s


