__d(function(t,e,n,o){"use strict";function i(){this.reinitializeTransaction(),this.reactMountReady=a.getPooled(null)}var a=e(137),r=e(138),c=e(144),u=(e(143),e(224)),s={initialize:function(){this.reactMountReady.reset()},close:function(){this.reactMountReady.notifyAll()}},l=[s],d={getTransactionWrappers:function(){return l},getReactMountReady:function(){return this.reactMountReady},getUpdateQueue:function(){return u},checkpoint:function(){return this.reactMountReady.checkpoint()},rollback:function(t){this.reactMountReady.rollback(t)},destructor:function(){a.release(this.reactMountReady),this.reactMountReady=null}};babelHelpers.extends(i.prototype,c,i,d),r.addPoolingTo(i),n.exports=i},223);