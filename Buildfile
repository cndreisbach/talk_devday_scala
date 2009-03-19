require 'buildr'
require 'buildr/scala'
include Java

desc 'Developer Day'
define 'devday' do
  project.version = '0.1'
  project.group = 'Clinton R. Nixon'

  repositories.remote << 'http://www.ibiblio.org/maven2'
  repositories.remote << "http://scala-tools.org/repo-releases"
  repositories.remote << "http://www.familie-kneissl.org/repo-releases"

  desc 'Scala API for Flying Saucer'
  define 'pdf_maker' do
    compile.with FLYING_SAUCER
  end

  desc 'Sinatra app to serve out PDFs from HTML'
  define 'pdf_server' do
    ;
  end

  desc 'Examples for presentation'
  define 'examples' do
    compile.with SCALATEST, SCALACHECK
  end
  
  desc 'slider'
  define 'slider' do
    compile.with VELOCITY, SCALA_VELOCITY, MARKDOWNJ, COMMONS_IO
  end
  
  desc 'Presentation'
  define 'presentation' do
    task 'build' do   
      load_dependencies
      puts "Building presentation..."
      slideshow = Java::Slideshow.new(_('scala.markdown'))
      slideshow.save_file
      
      # Java::Commands.java('Slider', _('scala.markdown'),
      #   :classpath => [
      #     project('slider').compile.dependencies, 
      #     project('slider')._('target/classes') ]
      # )
    end
    
    def load_dependencies
      project('slider').task('compile').invoke

      project('slider').compile.dependencies.each do |req|
        require req.to_s
      end
      $:.push(project('slider')._('target/classes'))
      
      %w(OptionDefinition *OptionDefinition OptionParser Slide Slideshow Slider).each do |prefix|
        Dir[project('slider')._("target/classes/#{prefix}*.class")].each do |java_class|
          require File.basename(java_class, ".class")
        end
      end
    end
  end
 
  SCALALIB = { :group => 'org.scala-lang', :id => 'scala-library', :version => '2.7.3' }
  SCALATEST = { :group => 'org.scala-tools.testing', :id => 'scalatest', :version => '0.9.5' }
  SCALACHECK = { :group => 'org.scala-tools.testing', :id => 'scalacheck', :version => '1.5' }
  SPECS = { :group => 'org.scala-tools.testing', :id => 'specs', :version => '1.4.3' }
  VELOCITY = { :group => 'org.apache.velocity', :id => 'velocity', :version => '1.6.1'}
  SCALA_VELOCITY = { :group => 'eu.mkneissl', :id => 'scala-velocity', :version => '0.8.0' }
  BEANSHELL_CORE = { :group => 'org.beanshell', :id => 'bsh-core', :version => '2.0b4' }
  FLYING_SAUCER = { :group => 'org.xhtmlrenderer', :id => 'core-renderer', :version => 'R8pre2' }
  COMMONS_IO = { :group => 'commons-io', :id => 'commons-io', :version => '1.4' }
  MARKDOWNJ = { :group => 'org.markdownj', :id => 'markdownj', :version => '0.3.0-1.0.2b4' }
end
