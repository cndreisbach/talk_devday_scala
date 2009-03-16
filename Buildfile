require 'buildr'
require 'buildr/scala'

desc 'Developer Day'
define 'devday' do
  project.version = '0.1'
  project.group = 'Clinton R. Nixon'

  desc 'Scala API for Flying Saucer'
  define 'pdf_maker' do
    fs_url = 'http://pigeonholdings.com/projects/flyingsaucer/downloads/r8/flyingsaucer-R8rc1.zip'
    fs_zip = download(_('target/fs.zip')=>fs_url)
    renderer_jar = file(_('target/xhtml_renderer/core-renderer.jar') => 
                        unzip(_('target/xhtml_renderer')=>fs_zip))
    itext_jar = file(_('target/xhtml_renderer/iText-2.0.8.jar') =>
                     unzip(_('target/xhtml_renderer')=>fs_zip))
    minium_jar = file(_('target/xhtml_renderer/minium.jar') => 
                      unzip(_('target/xhtml_renderer')=>fs_zip))
  
    compile.with renderer_jar, itext_jar, minium_jar
  end

  desc 'Sinatra app to serve out PDFs from HTML'
  define 'pdf_server' do

  end
end
