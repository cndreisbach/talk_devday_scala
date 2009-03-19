require 'buildr'
require 'buildr/scala'
require 'slideshow'

desc 'Developer Day'
define 'devday' do
  project.version = '0.1'
  project.group = 'Clinton R. Nixon'

  repositories.remote << 'http://www.ibiblio.org/maven2'
  repositories.remote << "http://scala-tools.org/repo-releases"
  repositories.remote << "http://www.familie-kneissl.org/repo-releases"

  SCALATEST = { :group => 'org.scala-tools.testing', :id => 'scalatest', :version => '0.9.5' }
  SCALACHECK = { :group => 'org.scala-tools.testing', :id => 'scalacheck', :version => '1.5' }
  SPECS = { :group => 'org.scala-tools.testing', :id => 'specs', :version => '1.4.3' }
  VELOCITY = { :group => 'org.apache.velocity', :id => 'velocity', :version => '1.6.1'}
  SCALA_VELOCITY = { :group => 'eu.mkneissl', :id => 'scala-velocity', :version => '0.8.0' }
  BEANSHELL_CORE = { :group => 'org.beanshell', :id => 'bsh-core', :version => '2.0b4' }
  FLYING_SAUCER = { :group => 'org.xhtmlrenderer', :id => 'core-renderer', :version => 'R8pre2' }
  MARKDOWNJ = { :group => 'org.markdownj', :id => 'markdownj', :version => '0.3.0-1.0.2b4' }
  COMMONS_IO = { :group => 'commons-io', :id => 'commons-io', :version => '1.4' }
  
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
    compile.with VELOCITY, SCALA_VELOCITY, SCALATEST, SCALACHECK, MARKDOWNJ, COMMONS_IO
  end
  
  desc 'Presentation'
  define 'presentation' do
    task 'build' do
      module Slideshow
        class Gen
          def highlight( lang_or_file, &blk )
            if block_given?
              content = capture_erb(&blk)
              return if content.empty?
              concat_erb( format_code(content, lang_or_file), blk.binding )
            else
              format_code(lang_or_file)
            end
          end

          def format_code(content, lang = nil)
            cmd = "pygmentize -f html "
            if lang
              cmd += "-l #{lang} "
            else
              cmd += content
            end
            
            IO.popen(cmd, "r+") do |pyg|
              pyg.write content unless lang.nil?
              pyg.close_write
              pyg.readlines.join('')
            end
          end
          
          def example(*ex)
            File.join(File.dirname(__FILE__), 'examples', 'src', 'main', 'scala', *ex)
          end
        end
      end
      
      Slideshow::Gen.new.run(['-t', _('template/s6.txt'),
                              '-o', _('slides'),
                              _('scala.markdown')])
    end
  end
end
