__d(function(e,t,n,r){"use strict";var a=t(91),i=t(25),s=i.AppState,o=t(95),p=t(18),c=function(e){function t(){babelHelpers.classCallCheck(this,t);var n=babelHelpers.possibleConstructorReturn(this,e.call(this,s));return n._eventHandlers={change:new Map,memoryWarning:new Map},n.currentState=s.initialAppState||"active",n.addListener("appStateDidChange",function(e){n.currentState=e.app_state}),s.getCurrentAppState(function(e){n.currentState=e.app_state},o),n}return babelHelpers.inherits(t,e),t.prototype.addEventListener=function(e,t){p(-1!==["change","memoryWarning"].indexOf(e),'Trying to subscribe to unknown event: "%s"',e),"change"===e?this._eventHandlers[e].set(t,this.addListener("appStateDidChange",function(e){t(e.app_state)})):"memoryWarning"===e&&this._eventHandlers[e].set(t,this.addListener("memoryWarning",t))},t.prototype.removeEventListener=function(e,t){p(-1!==["change","memoryWarning"].indexOf(e),'Trying to remove listener for unknown event: "%s"',e),this._eventHandlers[e].has(t)&&(this._eventHandlers[e].get(t).remove(),this._eventHandlers[e].delete(t))},t}(a);c=new c,n.exports=c},94);