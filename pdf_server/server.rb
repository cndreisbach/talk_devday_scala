require 'rubygems'
require 'sinatra'
require 'erb'
require 'maruku'

ROOT = File.expand_path(File.join(File.dirname(__FILE__), '..'))
$:.push ROOT

include Java

require "/usr/local/share/scala/lib/scala-library.jar"
Dir[ROOT + '/pdf_maker/target/fs/*.jar'].each do |jar|
  require jar
end

Dir[ROOT + '/pdf_maker/target/classes/*.class'].each do |java_class|
  require File.join(File.dirname(java_class), File.basename(java_class, ".class"))
end


get '/' do
  @pdfs = Dir[ROOT + '/pdf_server/public/pdf/*.pdf'].map { |fn| File.basename(fn) }
  render :erb, :index
end

post '/' do
  dir = ROOT + "/pdf_server"
  md_filename = params[:markdown_doc][:filename]
  basename = File.basename(md_filename, File.extname(md_filename))
  html_filename = dir + "/public/html/#{basename}.html"
  pdf_filename = dir + "/public/pdf/#{basename}.pdf"
  
  FileUtils.mkdir_p(dir + '/public/html')
  FileUtils.mkdir_p(dir + '/public/pdf')

  md = Maruku.new(params[:markdown_doc][:tempfile].read)

  File.open(html_filename, 'w') do |html|
    html.puts md.to_html_document
  end

  pdf_maker = Java::PDFMaker.new(html_filename)
  pdf_maker.create_pdf(pdf_filename)

  redirect '/'
end

