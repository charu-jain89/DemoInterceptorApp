# DemoInterceptorApp

I have a TestInterceptor class whose before() method looks like:
 
    class TestInterceptor {
     
       TestInterceptor () {
            matchAll().excludes(controller: 'abc')
        }
        
      boolean before() {
             println "controllerName= ${controllerName}, actionName= ${actionName},  params = ${params}"
             // some code to execute here
           }
    }

This is supposed to work for all controllers, actions except abcController.

Now, when I hit a 'DemoController', 'test' action. Interceptor successfully printed me :

    controllerName == demo, actionName == test, params == [controller:demo, action:test] // which is correct

Here, 'test' action renders a gsp, which has some javascript files to include on the page like:

   <script type="text/javascript" src="${resource(dir: 'js', file: 'file1.js')}"></script>

Problem arise here.

Interceptor executes its before() method and its corresponding code here and prints:

    controllerName= static, actionName= js,  params = [controller:static, format:js, action:js, id:file1]

This is not desirable as I don't want to execute my before() method except call to any Controller.


Actual outputs:
```
Running application...
Grails application running at http://localhost:8080/demoInterceptorApp in environment: development
controllerName= null, actionName= null,  params = [:] // undesirable
controllerName= demo, actionName= test,  params = [controller:demo, format:null, action:test]
Testing....
controllerName= static, actionName= js,  params = [controller:static, format:js, action:js, id:file1] //undesirable
```

Expected outputs:
```
Running application...
Grails application running at http://localhost:8080/demoInterceptorApp in environment: development
controllerName= demo, actionName= test,  params = [controller:demo, format:null, action:test]
Testing....
```
