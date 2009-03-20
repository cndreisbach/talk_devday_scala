require 'buildr'
require 'buildr/scala'
include Java

desc 'Developer Day'
define 'devday' do
  project.version = '0.1'
  project.group = 'Clinton R. Nixon'

  desc 'Slideshow creator'
  define 'slider' do
    compile.with MARKDOWNJ, COMMONS_IO, FLYING_SAUCER, ITEXT
  end
  
  desc 'Presentation'
  define 'presentation' do
    compile.with SCALATEST, SCALACHECK

    build do
      load_dependencies
      puts "Building presentation..."
      slideshow = Java::Slideshow.new(_('scala.markdown'))
      slideshow.save_html
    end
    
    task 'pdf' do
      load_dependencies
      puts "Building presentation PDF..."
      slideshow = Java::Slideshow.new(_('scala.markdown'))
      slideshow.save_pdf
    end
    
    def load_dependencies
      project('slider').task('compile').invoke
      project('slider').compile.dependencies.each { |req| require req.to_s }
      $:.push(project('slider')._('target/classes'))
      
      %w(OptionDefinition *OptionDefinition OptionParser 
          Slide Slideshow Slider PDFMaker).each do |prefix|
        Dir[project('slider')._("target/classes/#{prefix}*.class")].each do |java_class|
          require File.basename(java_class, ".class")
        end
      end
    end
  end

  repositories.remote << 'http://www.ibiblio.org/maven2'
  repositories.remote << "http://scala-tools.org/repo-releases"
  repositories.remote << "http://www.familie-kneissl.org/repo-releases"
 
  SCALALIB = { :group => 'org.scala-lang', :id => 'scala-library', :version => '2.7.3' }
  SCALATEST = { :group => 'org.scala-tools.testing', :id => 'scalatest', :version => '0.9.5' }
  SCALACHECK = { :group => 'org.scala-tools.testing', :id => 'scalacheck', :version => '1.5' }
  SPECS = { :group => 'org.scala-tools.testing', :id => 'specs', :version => '1.4.3' }
  FLYING_SAUCER = { :group => 'org.xhtmlrenderer', :id => 'core-renderer', :version => 'R8pre2' }
  ITEXT = { :group => 'com.lowagie', :id => 'itext', :version => '2.0.8' }
  COMMONS_IO = { :group => 'commons-io', :id => 'commons-io', :version => '1.4' }
  MARKDOWNJ = { :group => 'org.markdownj', :id => 'markdownj', :version => '0.3.0-1.0.2b4' }
end
