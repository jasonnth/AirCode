__d(function(t,e,n,s){"use strict";function i(){return F++}function r(t){if(null===t||"object"!=typeof t)return String(t);var e="__navigatorRouteID";return t.hasOwnProperty(e)||Object.defineProperty(t,e,{enumerable:!1,configurable:!1,writable:!1,value:i()}),t[e]}var a=e(25).AnimationsDebugModule,o=e(84),u=e(277),h=e(278),c=e(283),d=e(289),l=e(290),p=e(291),v=e(60),f=e(82),g=e(257),_=e(179),S=e(237),m=e(113),x=e(293),I=e(53),b=e(18),G=e(294),R=v.PropTypes,y=o.get("window").width,k=o.get("window").height,C={pointerEvents:"none",style:{top:k,bottom:-k,opacity:0}},F=0,T=f.create({container:{flex:1,overflow:"hidden"},defaultSceneStyle:{position:"absolute",left:0,right:0,bottom:0,top:0,transform:[{translateX:0},{translateY:0},{scaleX:1},{scaleY:1},{rotate:"0deg"},{skewX:"0deg"},{skewY:"0deg"}]},baseScene:{position:"absolute",overflow:"hidden",left:0,right:0,bottom:0,top:0},disabledScene:{top:k,bottom:-k},transitioner:{flex:1,backgroundColor:"transparent",overflow:"hidden"}}),w=["pop","jumpBack","jumpForward"],P=v.createClass({displayName:"Navigator",propTypes:{configureScene:R.func,renderScene:R.func.isRequired,initialRoute:R.object,initialRouteStack:R.arrayOf(R.object),onWillFocus:R.func,onDidFocus:R.func,navigationBar:R.node,navigator:R.object,sceneStyle:m.propTypes.style},statics:{BreadcrumbNavigationBar:c,NavigationBar:d,SceneConfigs:l},mixins:[S,u,g.Mixin],getDefaultProps:function(){return{configureScene:function(){return l.PushFromRight},sceneStyle:T.defaultSceneStyle}},getInitialState:function(){var t=this;this._navigationBarNavigator=this.props.navigationBarNavigator||this,this._renderedSceneMap=new Map,this._sceneRefs=[];var e=this.props.initialRouteStack||[this.props.initialRoute];b(e.length>=1,"Navigator requires props.initialRoute or props.initialRouteStack.");var n=e.length-1;return this.props.initialRoute&&(n=e.indexOf(this.props.initialRoute),b(-1!==n,"initialRoute is not in initialRouteStack.")),{sceneConfigStack:e.map(function(n){return t.props.configureScene(n,e)}),routeStack:e,presentedIndex:n,transitionFromIndex:null,activeGesture:null,pendingGestureProgress:null,transitionQueue:[]}},componentWillMount:function(){var t=this;this.__defineGetter__("navigationContext",this._getNavigationContext),this._subRouteFocus=[],this.parentNavigator=this.props.navigator,this._handlers={},this.springSystem=new G.SpringSystem,this.spring=this.springSystem.createSpring(),this.spring.setRestSpeedThreshold(.05),this.spring.setCurrentValue(0).setAtRest(),this.spring.addListener({onSpringEndStateChange:function(){t._interactionHandle||(t._interactionHandle=t.createInteractionHandle())},onSpringUpdate:function(){t._handleSpringUpdate()},onSpringAtRest:function(){t._completeTransition()}}),this.panGesture=p.create({onMoveShouldSetPanResponder:this._handleMoveShouldSetPanResponder,onPanResponderRelease:this._handlePanResponderRelease,onPanResponderMove:this._handlePanResponderMove,onPanResponderTerminate:this._handlePanResponderTerminate}),this._interactionHandle=null,this._emitWillFocus(this.state.routeStack[this.state.presentedIndex])},componentDidMount:function(){this._handleSpringUpdate(),this._emitDidFocus(this.state.routeStack[this.state.presentedIndex]),this._enableTVEventHandler()},componentWillUnmount:function(){this._navigationContext&&(this._navigationContext.dispose(),this._navigationContext=null),this.spring.destroy(),this._interactionHandle&&this.clearInteractionHandle(this._interactionHandle),this._disableTVEventHandler()},immediatelyResetRouteStack:function(t){var e=this,n=t.length-1;this._emitWillFocus(t[n]),this.setState({routeStack:t,sceneConfigStack:t.map(function(n){return e.props.configureScene(n,t)}),presentedIndex:n,activeGesture:null,transitionFromIndex:null,transitionQueue:[]},function(){e._handleSpringUpdate();var t=e._navBar;t&&t.immediatelyRefresh&&t.immediatelyRefresh(),e._emitDidFocus(e.state.routeStack[e.state.presentedIndex])})},_transitionTo:function(t,e,n,s){if(this.state.presentedIndex===t)return void(s&&s());if(null!==this.state.transitionFromIndex)return void this.state.transitionQueue.push({destIndex:t,velocity:e,cb:s});this.state.transitionFromIndex=this.state.presentedIndex,this.state.presentedIndex=t,this.state.transitionCb=s,this._onAnimationStart(),a&&a.startRecordingFps();var i=this.state.sceneConfigStack[this.state.transitionFromIndex]||this.state.sceneConfigStack[this.state.presentedIndex];b(i,"Cannot configure scene at index "+this.state.transitionFromIndex),null!=n&&this.spring.setCurrentValue(n),this.spring.setOvershootClampingEnabled(!0),this.spring.getSpringConfig().friction=i.springFriction,this.spring.getSpringConfig().tension=i.springTension,this.spring.setVelocity(e||i.defaultTransitionVelocity),this.spring.setEndValue(1)},_handleSpringUpdate:function(){if(this.isMounted())if(null!=this.state.transitionFromIndex)this._transitionBetween(this.state.transitionFromIndex,this.state.presentedIndex,this.spring.getCurrentValue());else if(null!=this.state.activeGesture){var t=this.state.presentedIndex+this._deltaForGestureAction(this.state.activeGesture);this._transitionBetween(this.state.presentedIndex,t,this.spring.getCurrentValue())}},_completeTransition:function(){if(this.isMounted()){if(1!==this.spring.getCurrentValue()&&0!==this.spring.getCurrentValue())return void(this.state.pendingGestureProgress&&(this.state.pendingGestureProgress=null));this._onAnimationEnd();var t=this.state.presentedIndex,e=this._subRouteFocus[t]||this.state.routeStack[t];if(a&&a.stopRecordingFps(Date.now()),this.state.transitionFromIndex=null,this.spring.setCurrentValue(0).setAtRest(),this._hideScenes(),this.state.transitionCb&&(this.state.transitionCb(),this.state.transitionCb=null),this._emitDidFocus(e),this._interactionHandle&&(this.clearInteractionHandle(this._interactionHandle),this._interactionHandle=null),this.state.pendingGestureProgress){var n=this.state.presentedIndex+this._deltaForGestureAction(this.state.activeGesture);return this._enableScene(n),void this.spring.setEndValue(this.state.pendingGestureProgress)}if(this.state.transitionQueue.length){var s=this.state.transitionQueue.shift();this._enableScene(s.destIndex),this._emitWillFocus(this.state.routeStack[s.destIndex]),this._transitionTo(s.destIndex,s.velocity,null,s.cb)}}},_emitDidFocus:function(t){this.navigationContext.emit("didfocus",{route:t}),this.props.onDidFocus&&this.props.onDidFocus(t)},_emitWillFocus:function(t){this.navigationContext.emit("willfocus",{route:t});var e=this._navBar;e&&e.handleWillFocus&&e.handleWillFocus(t),this.props.onWillFocus&&this.props.onWillFocus(t)},_hideScenes:function(){var t=null;this.state.activeGesture&&(t=this.state.presentedIndex+this._deltaForGestureAction(this.state.activeGesture));for(var e=0;e<this.state.routeStack.length;e++)e!==this.state.presentedIndex&&e!==this.state.transitionFromIndex&&e!==t&&this._disableScene(e)},_disableScene:function(t){this._sceneRefs[t]&&this._sceneRefs[t].setNativeProps(C)},_enableScene:function(t){var e=I([T.baseScene,this.props.sceneStyle]),n={pointerEvents:"auto",style:{top:e.top,bottom:e.bottom}};t!==this.state.transitionFromIndex&&t!==this.state.presentedIndex&&(n.style.opacity=0),this._sceneRefs[t]&&this._sceneRefs[t].setNativeProps(n)},_clearTransformations:function(t){var e=I([T.defaultSceneStyle]);this._sceneRefs[t].setNativeProps({style:e})},_onAnimationStart:function(){var t=this.state.presentedIndex,e=this.state.presentedIndex;null!=this.state.transitionFromIndex?t=this.state.transitionFromIndex:this.state.activeGesture&&(e=this.state.presentedIndex+this._deltaForGestureAction(this.state.activeGesture)),this._setRenderSceneToHardwareTextureAndroid(t,!0),this._setRenderSceneToHardwareTextureAndroid(e,!0);var n=this._navBar;n&&n.onAnimationStart&&n.onAnimationStart(t,e)},_onAnimationEnd:function(){for(var t=this.state.routeStack.length-1,e=0;e<=t;e++)this._setRenderSceneToHardwareTextureAndroid(e,!1);var n=this._navBar;n&&n.onAnimationEnd&&n.onAnimationEnd()},_setRenderSceneToHardwareTextureAndroid:function(t,e){var n=this._sceneRefs[t];null!==n&&void 0!==n&&n.setNativeProps({renderToHardwareTextureAndroid:e})},_handleTouchStart:function(){this._eligibleGestures=w},_handleMoveShouldSetPanResponder:function(t,e){var n=this.state.sceneConfigStack[this.state.presentedIndex];return!!n&&(this._expectingGestureGrant=this._matchGestureAction(this._eligibleGestures,n.gestures,e),!!this._expectingGestureGrant)},_doesGestureOverswipe:function(t){var e=this.state.presentedIndex<=0&&("pop"===t||"jumpBack"===t);return this.state.presentedIndex>=this.state.routeStack.length-1&&"jumpForward"===t||e},_deltaForGestureAction:function(t){switch(t){case"pop":case"jumpBack":return-1;case"jumpForward":return 1;default:return void b(!1,"Unsupported gesture action "+t)}},_handlePanResponderRelease:function(t,e){var n=this,s=this.state.sceneConfigStack[this.state.presentedIndex],i=this.state.activeGesture;if(i){var r=s.gestures[i],a=this.state.presentedIndex+this._deltaForGestureAction(this.state.activeGesture);if(0===this.spring.getCurrentValue())return this.spring.setCurrentValue(0).setAtRest(),void this._completeTransition();var o,u,h="top-to-bottom"===r.direction||"bottom-to-top"===r.direction,c="right-to-left"===r.direction||"bottom-to-top"===r.direction;h?(o=c?-e.vy:e.vy,u=c?-e.dy:e.dy):(o=c?-e.vx:e.vx,u=c?-e.dx:e.dx);var d=x(-10,o,10);if(Math.abs(o)<r.notMoving){d=u>r.fullDistance*r.stillCompletionRatio?r.snapVelocity:-r.snapVelocity}if(d<0||this._doesGestureOverswipe(i)){if(null==this.state.transitionFromIndex){var l=this.state.presentedIndex;this.state.presentedIndex=a,this._transitionTo(l,-d,1-this.spring.getCurrentValue())}}else this._emitWillFocus(this.state.routeStack[a]),this._transitionTo(a,d,null,function(){"pop"===i&&n._cleanScenesPastIndex(a)});this._detachGesture()}},_handlePanResponderTerminate:function(t,e){if(null!=this.state.activeGesture){var n=this.state.presentedIndex+this._deltaForGestureAction(this.state.activeGesture);this._detachGesture();var s=this.state.presentedIndex;this.state.presentedIndex=n,this._transitionTo(s,null,1-this.spring.getCurrentValue())}},_attachGesture:function(t){this.state.activeGesture=t;var e=this.state.presentedIndex+this._deltaForGestureAction(this.state.activeGesture);this._enableScene(e)},_detachGesture:function(){this.state.activeGesture=null,this.state.pendingGestureProgress=null,this._hideScenes()},_handlePanResponderMove:function(t,e){void 0!==this._isMoveGestureAttached&&(b(this._expectingGestureGrant,"Responder granted unexpectedly."),this._attachGesture(this._expectingGestureGrant),this._onAnimationStart(),this._expectingGestureGrant=void 0);var n=this.state.sceneConfigStack[this.state.presentedIndex];if(this.state.activeGesture){var s=n.gestures[this.state.activeGesture];return this._moveAttachedGesture(s,e)}var i=this._matchGestureAction(w,n.gestures,e);i&&this._attachGesture(i)},_moveAttachedGesture:function(t,e){var n="top-to-bottom"===t.direction||"bottom-to-top"===t.direction,s="right-to-left"===t.direction||"bottom-to-top"===t.direction,i=n?e.dy:e.dx;i=s?-i:i;var r=t.gestureDetectMovement,a=(i-r)/(t.fullDistance-r);if(a<0&&t.isDetachable){var o=this.state.presentedIndex+this._deltaForGestureAction(this.state.activeGesture);return this._transitionBetween(this.state.presentedIndex,o,0),this._detachGesture(),void(null!=this.state.pendingGestureProgress&&this.spring.setCurrentValue(0))}if(t.overswipe&&this._doesGestureOverswipe(this.state.activeGesture)){var u=t.overswipe.frictionConstant,h=t.overswipe.frictionByDistance;a*=1/(u+Math.abs(a)*h)}a=x(0,a,1),null!=this.state.transitionFromIndex?this.state.pendingGestureProgress=a:this.state.pendingGestureProgress?this.spring.setEndValue(a):this.spring.setCurrentValue(a)},_matchGestureAction:function(t,e,n){var s=this;if(!e||!t||!t.some)return null;var i=null;return t.some(function(t,r){var a=e[t];if(a){if(null==a.overswipe&&s._doesGestureOverswipe(t))return!1;var o="top-to-bottom"===a.direction||"bottom-to-top"===a.direction,u="right-to-left"===a.direction||"bottom-to-top"===a.direction,h=o?n.y0:n.x0,c=o?n.moveY:n.moveX,d=o?n.dy:n.dx,l=o?n.dx:n.dy,p=a.edgeHitWidth;u&&(h=-h,c=-c,d=-d,l=-l,p=o?-(k-p):-(y-p)),0===h&&(h=c);if(!(null==a.edgeHitWidth||h<p))return!1;if(!(d>=a.gestureDetectMovement))return!1;if(Math.abs(d)>Math.abs(l)*a.directionRatio)return i=t,!0;s._eligibleGestures=s._eligibleGestures.slice().splice(r,1)}}),i||null},_transitionSceneStyle:function(t,e,n,s){var i=this._sceneRefs[s];if(null!==i&&void 0!==i){var r=t<e?e:t,a=this.state.sceneConfigStack[r];a||(a=this.state.sceneConfigStack[r-1]);var o={};(s<t||s<e?a.animationInterpolators.out:a.animationInterpolators.into)(o,t<e?n:1-n)&&i.setNativeProps({style:o})}},_transitionBetween:function(t,e,n){this._transitionSceneStyle(t,e,n,t),this._transitionSceneStyle(t,e,n,e);var s=this._navBar;s&&s.updateProgress&&e>=0&&t>=0&&s.updateProgress(n,t,e)},_handleResponderTerminationRequest:function(){return!1},_getDestIndexWithinBounds:function(t){var e=this.state.presentedIndex,n=e+t;b(n>=0,"Cannot jump before the first route.");var s=this.state.routeStack.length-1;return b(s>=n,"Cannot jump past the last route."),n},_jumpN:function(t){var e=this._getDestIndexWithinBounds(t);this._enableScene(e),this._emitWillFocus(this.state.routeStack[e]),this._transitionTo(e)},jumpTo:function(t){var e=this.state.routeStack.indexOf(t);b(-1!==e,"Cannot jump to route that is not in the route stack"),this._jumpN(e-this.state.presentedIndex)},jumpForward:function(){this._jumpN(1)},jumpBack:function(){this._jumpN(-1)},push:function(t){var e=this;b(!!t,"Must supply route to push");var n=this.state.presentedIndex+1,s=this.state.routeStack.slice(0,n),i=this.state.sceneConfigStack.slice(0,n),r=s.concat([t]),a=r.length-1,o=this.props.configureScene(t,r),u=i.concat([o]);this._emitWillFocus(r[a]),this.setState({routeStack:r,sceneConfigStack:u},function(){e._enableScene(a),e._transitionTo(a,o.defaultTransitionVelocity)})},popN:function(t){var e=this;if(b("number"==typeof t,"Must supply a number to popN"),!((t=parseInt(t,10))<=0||this.state.presentedIndex-t<0)){var n=this.state.presentedIndex-t,s=this.state.routeStack[this.state.presentedIndex],i=this.props.configureScene(s);this._enableScene(n),this._clearTransformations(n),this._emitWillFocus(this.state.routeStack[n]),this._transitionTo(n,i.defaultTransitionVelocity,null,function(){e._cleanScenesPastIndex(n)})}},pop:function(){this.state.transitionQueue.length||this.popN(1)},replaceAtIndex:function(t,e,n){var s=this;if(b(!!t,"Must supply route to replace"),e<0&&(e+=this.state.routeStack.length),!(this.state.routeStack.length<=e)){var i=this.state.routeStack.slice(),r=this.state.sceneConfigStack.slice();i[e]=t,r[e]=this.props.configureScene(t,i),e===this.state.presentedIndex&&this._emitWillFocus(t),this.setState({routeStack:i,sceneConfigStack:r},function(){e===s.state.presentedIndex&&s._emitDidFocus(t),n&&n()})}},replace:function(t){this.replaceAtIndex(t,this.state.presentedIndex)},replacePrevious:function(t){this.replaceAtIndex(t,this.state.presentedIndex-1)},popToTop:function(){this.popToRoute(this.state.routeStack[0])},popToRoute:function(t){var e=this.state.routeStack.indexOf(t);b(-1!==e,"Calling popToRoute for a route that doesn't exist!");var n=this.state.presentedIndex-e;this.popN(n)},replacePreviousAndPop:function(t){this.state.routeStack.length<2||(this.replacePrevious(t),this.pop())},resetTo:function(t){var e=this;b(!!t,"Must supply route to push"),this.replaceAtIndex(t,0,function(){e.popN(e.state.presentedIndex)})},getCurrentRoutes:function(){return this.state.routeStack.slice()},_cleanScenesPastIndex:function(t){var e=t+1;e<this.state.routeStack.length&&this.setState({sceneConfigStack:this.state.sceneConfigStack.slice(0,e),routeStack:this.state.routeStack.slice(0,e)})},_renderScene:function(t,e){var n=this,s=null,i="auto";return e!==this.state.presentedIndex&&(s=T.disabledScene,i="none"),v.createElement(m,{key:"scene_"+r(t),ref:function(t){n._sceneRefs[e]=t},onStartShouldSetResponderCapture:function(){return null!=n.state.transitionFromIndex},pointerEvents:i,style:[T.baseScene,this.props.sceneStyle,s]},this.props.renderScene(t,this))},_renderNavigationBar:function(){var t=this,e=this.props.navigationBar;return e?v.cloneElement(e,{ref:function(n){t._navBar=n,e&&"function"==typeof e.ref&&e.ref(n)},navigator:this._navigationBarNavigator,navState:this.state}):null},_tvEventHandler:_,_enableTVEventHandler:function(){this._tvEventHandler=new _,this._tvEventHandler.enable(this,function(t,e){e&&"menu"===e.eventType&&t.pop()})},_disableTVEventHandler:function(){this._tvEventHandler&&(this._tvEventHandler.disable(),delete this._tvEventHandler)},render:function(){var t=this,e=new Map,n=this.state.routeStack.map(function(n,s){var i;return i=t._renderedSceneMap.has(n)&&s!==t.state.presentedIndex?t._renderedSceneMap.get(n):t._renderScene(n,s),e.set(n,i),i});return this._renderedSceneMap=e,babelHelpers.jsx(m,{style:[T.container,this.props.style]},void 0,v.createElement(m,babelHelpers.extends({style:T.transitioner},this.panGesture.panHandlers,{onTouchStart:this._handleTouchStart,onResponderTerminationRequest:this._handleResponderTerminationRequest}),n),this._renderNavigationBar())},_getNavigationContext:function(){return this._navigationContext||(this._navigationContext=new h),this._navigationContext}});n.exports=P},276);