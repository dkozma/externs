(set-env!
 :resource-paths #{"resources"}
 :dependencies '[[cljsjs/boot-cljsjs "0.5.2"  :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "3.0.2")
(def +version+ (str +lib-version+ "-1"))

(task-options!
 pom {:project     'cljsjs/react-three-renderer
      :version     +version+
      :description "Render into a three.js canvas using React."
      :url         "https://toxicfork.github.com/react-three-renderer-example/"
      :scm         {:url "https://github.com/toxicFork/react-three-renderer"}
      :license     {"MIT" "http://opensource.org/licenses/MIT"}})

(deftask package []
  (comp
   (download  :url      "https://cdn.rawgit.com/dkozma/4527ca9ad86f2650547d50ed068d63e1/raw/b667ee8eaa5fcfade21344667a6cd703e7644ae2/r3r.js"
              :checksum "9D3046525E4B7D123739BF621A9C4779")
   (download  :url      "https://cdn.rawgit.com/dkozma/9e3b1161c1c417d3c22001571848cd32/raw/9f5550ae07759bb882a301efa081ef57bd07f4cb/r3r.min.js"
              :checksum "49EF6E6B49C34492F12A6459E4B361FC")
   (sift      :move     {#"^r3r.js"
                         "cljsjs/react-three-renderer/development/react-three-renderer.inc.js"
                         #"^r3r.min.js"
                         "cljsjs/react-three-renderer/production/react-three-renderer.min.inc.js"})
   (sift      :include  #{#"^cljsjs"})
   (deps-cljs :name     "cljsjs.react-three-renderer")
   (pom)
   (jar)))
