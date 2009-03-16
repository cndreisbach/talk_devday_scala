require 'rubygems'
require 'sinatra'
require 'erb'
require 'maruku'

class Object
  def tap
    yield self
    self
  end
end

include Java
require "/usr/local/share/scala/lib/scala-library.jar"
Dir[File.dirname(__FILE__) + '/../pdf_maker/target/xhtml_renderer/*.jar'].each do |jar|
  require jar.tap { |j| p j }
end

Dir[File.dirname(__FILE__) + '/../pdf_maker/target/classes/*.class'].each do |java_class|
  require File.join(File.dirname(java_class), File.basename(java_class, ".class")).tap { |j| p j }
end

set :app_file, __FILE__
set :reload, true

get '/' do
  # get list of uploads
  render :erb, :index
end

post '/' do
  md_filename = params[:markdown_doc][:filename]
  html_filename = File.basename(md_filename, File.extname(md_filename)) + ".html"
  pdf_filename = File.basename(html_filename, ".html") + ".pdf"
  FileUtils.mkdir_p('uploads')
  Dir.chdir 'uploads' do
    md = Maruku.new(params[:markdown_doc][:tempfile].read)

    File.open(html_filename, 'w') do |html|
      html.puts md.to_html_document
    end

    pdf_maker = Java::PDFMaker.new(File.expand_path(html_filename))
    pdf_maker.create_pdf(File.expand_path(pdf_filename))
  end


  redirect '/'
end

