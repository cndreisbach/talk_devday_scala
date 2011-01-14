require 'buildr'
require 'buildr/scala'

include Java

repositories.remote << 'http://www.ibiblio.org/maven2'
repositories.remote << "http://scala-tools.org/repo-releases"
repositories.remote << "http://www.familie-kneissl.org/repo-releases"

SCALALIB = { :group => 'org.scala-lang', :id => 'scala-library', :version => '2.8.1' }
SCALACOMP = { :group => 'org.scala-lang', :id => 'scala-compiler', :version => '2.8.1' }
SCALATEST = { :group => 'org.scalatest', :id => 'scalatest', :version => '1.2' }
SCALACHECK = { :group => 'org.scala-tools.testing', :id => 'scalacheck_2.8.1', :version => '1.8' }
SPECS = { :group => 'org.scala-tools.testing', :id => 'specs', :version => '1.6.7' }
FLYING_SAUCER = { :group => 'org.xhtmlrenderer', :id => 'core-renderer', :version => 'R8pre2' }
ITEXT = { :group => 'com.lowagie', :id => 'itext', :version => '2.0.8' }
COMMONS_IO = { :group => 'commons-io', :id => 'commons-io', :version => '1.4' }
MARKDOWNJ = { :group => 'org.markdownj', :id => 'markdownj', :version => '0.3.0-1.0.2b4' }

desc 'Developer Day'
define 'devday' do
  project.version = '0.1'
  project.group = 'Clinton R. Nixon'

  desc 'Slideshow creator'
  define 'slider' do
    compile.with SCALACOMP, SCALALIB, MARKDOWNJ, COMMONS_IO, FLYING_SAUCER, ITEXT
  end
  
  desc 'Presentation'
  define 'presentation' do
    compile.with SCALATEST, SCALALIB

    build do
      puts "Building presentation..."
      Commands.java 'SlideToHTML', _('scala.markdown'), 
        :classpath => [ project('slider').compile.dependencies,
                        project('slider')._('target/classes'),
                        SCALALIB ]
    end
    
    task 'pdf' do
      puts "Building presentation PDF..."
      Commands.java 'SlideToPDF', _('scala.markdown'), 
        :classpath => [ project('slider').compile.dependencies,
                        project('slider')._('target/classes'),
                        SCALALIB ]
    end    
  end
end
