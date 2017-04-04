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
   (download  :url      "https://cdn.rawgit.com/dkozma/externs/master/react-three-renderer/build/bundle.js"
              :checksum "752CB64AF70A03EA5B4C88747E8A37AD")
   (sift      :move     {#"^bundle.js"
                         "cljsjs/react-three-renderer/development/react-three-renderer.inc.js"})
   (sift      :include  #{#"^cljsjs"})
   (deps-cljs :name     "cljsjs.react-three-renderer")
   (pom)
   (jar)))
