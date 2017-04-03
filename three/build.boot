(set-env!
 :resource-paths #{"resources"}
 :dependencies '[[cljsjs/boot-cljsjs "0.5.2"  :scope "test"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "0.0.82")
(def +version+ (str +lib-version+ "-0"))

(task-options!
 pom {:project     'cljsjs/three
      :version     +version+
      :description "JavaScript 3D library"
      :url         "http://threejs.org/"
      :scm         {:url "https://github.com/mrdoob/three.js"}
      :license     {"MIT" "http://opensource.org/licenses/MIT"}})

(deftask package []
  (comp
   (download  :url      "https://raw.githubusercontent.com/mrdoob/three.js/r82/build/three.js"
              :checksum "B3BB026EF19A9E48E0429B38744DE3BA")
   (download  :url      "https://raw.githubusercontent.com/mrdoob/three.js/r82/build/three.min.js"
              :checksum "27B214112CF8CD61B9CFEE9D38415C4E")
   (sift      :move     {#"^three.js"
                         "cljsjs/three/development/three.inc.js"
                         #"^three.min.js"
                         "cljsjs/three/production/three.min.inc.js"})
   (sift      :include  #{#"^cljsjs"})
   (deps-cljs :name     "cljsjs.three")
   (pom)
   (jar)))
