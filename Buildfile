require 'buildr'
require 'buildr/scala'

desc 'Scala API for Flying Saucer'
define 'pdf-maker' do
  project.version = '0.1'
  project.group = 'Clinton R. Nixon'
  
  fs_url = 'http://pigeonholdings.com/projects/flyingsaucer/downloads/r8/flyingsaucer-R8rc1.zip'
  fs_zip = download('target/fs.zip'=>fs_url)
  renderer_jar = file('target/fs/core-renderer.jar' => 
    unzip('target/fs'=>fs_zip))
  itext_jar = file('target/fs/iText-2.0.8.jar' =>
    unzip('target/fs'=>fs_zip))
  minium_jar = file('target/fs/minium.jar' => 
    unzip('target/fs'=>fs_zip))

  compile.with renderer_jar, itext_jar, minium_jar
end