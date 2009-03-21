require 'rubygems'
require 'sinatra'
require 'erb'
require 'maruku'

ROOT = File.expand_path(File.dirname(__FILE__))
$:.push ROOT

include Java

[ '~/.m2/repository/org/scala-lang/**/*.jar',
  '~/.m2/repository/com/lowagie/**/*.jar',
  '~/.m2/repository/org/xhtmlrenderer/**/*.jar'].each do |splat_path|
  Dir[File.expand_path(splat_path)].each do |jar|
    p jar
    require jar
  end
end

$:.push(ROOT + '/../../../../../slider/target/classes/')

Dir[ROOT + '/../../../../../slider/target/classes/PDFMaker*.class'].each do |java_class|
  p File.basename(java_class, '.class')
  require File.basename(java_class, '.class')
end


get '/' do
  @pdfs = Dir[ROOT + '/public/pdf/*.pdf'].map { |fn| File.basename(fn) }
  render :erb, :index
end

post '/' do
  md_filename = params[:markdown_doc][:filename]
  basename = File.basename(md_filename, File.extname(md_filename))
  html_filename = ROOT + "/public/html/#{basename}.html"
  pdf_filename = ROOT + "/public/pdf/#{basename}.pdf"
  
  FileUtils.mkdir_p(ROOT + '/public/html')
  FileUtils.mkdir_p(ROOT + '/public/pdf')

  md = Maruku.new(params[:markdown_doc][:tempfile].read)

  File.open(html_filename, 'w') do |html|
    html.puts md.to_html_document
  end

  pdf_maker = Java::PDFMaker.new(html_filename)
  pdf_maker.create_pdf(pdf_filename)

  redirect '/'
end

