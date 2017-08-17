package demointerceptorapp

class TestInterceptor {

  TestInterceptor() {
    matchAll().excludes(controller: 'abc')
  }

  boolean before() {
    println "controllerName= ${controllerName}, actionName= ${actionName},  params = ${params}"
    // some code to execute here
    true
  }

  boolean after() { true }

  void afterView() {
    // no-op
  }
}