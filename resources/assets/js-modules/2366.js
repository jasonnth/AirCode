__d(function(e,n,o,t){"use strict";function r(e){e.moveX=0,e.moveY=0,e.x0=0,e.y0=0,e.dx=0,e.dy=0,e.vx=0,e.vy=0,e.numberActiveTouches=0,e._accountsForMovesUpTo=0,e.previousMoveX=0,e.previousMoveY=0,e.pinch=void 0,e.previousPinch=void 0,e.singleTapUp=!1,e.doubleTapUp=!1,e._singleTabFailed=!1}function a(e,n,o){var t=e._accountsForMovesUpTo,r=S(n,t),a=T(n,t),s=b(n,t),u=f(n,t),d=a-r,p=u-s;e.numberActiveTouches=n.numberActiveTouches,e.moveX=a,e.moveY=u;var c=i(n.mostRecentTimeStamp-t);e.vx=d/c,e.vy=p/c,e.dx+=d,e.dy+=p,e._accountsForMovesUpTo=n.mostRecentTimeStamp,e.previousMoveX=r,e.previousMoveY=s,e.pinch=(0,m.pinchDistance)(n,t,!0),e.previousPinch=(0,m.pinchDistance)(n,t,!1)}function s(e){e.handle&&(c.InteractionManager.clearInteractionHandle(e.handle),e.handle=null)}function i(e){return e>1e6?e/1e6:e}function u(e){void 0!==e._singleTapConfirmId&&(R.default.clearTimeout(e._singleTapConfirmId),e._singleTapConfirmId=void 0)}function d(e){e.debug&&(H=!0);var n={handle:null},o={stateID:Math.random()};r(o);var t={onStartShouldSetResponder:function(n){return u(o),!!e.onStartShouldSetResponder&&e.onStartShouldSetResponder(n,o)},onMoveShouldSetResponder:function(n){return!(!e.onMoveShouldSetResponder||!p(e,o))&&e.onMoveShouldSetResponder(n,o)},onStartShouldSetResponderCapture:function(n){return u(o),1===n.nativeEvent.touches.length&&r(o),o.numberActiveTouches=n.touchHistory.numberActiveTouches,!!e.onStartShouldSetResponderCapture&&e.onStartShouldSetResponderCapture(n,o)},onMoveShouldSetResponderCapture:function(n){var t=n.touchHistory;return o._accountsForMovesUpTo!==t.mostRecentTimeStamp&&(a(o,t,n),!(!e.onMoveShouldSetResponderCapture||!p(e,o))&&e.onMoveShouldSetResponderCapture(n,o))},onResponderGrant:function(t){return u(o),n.handle||(n.handle=c.InteractionManager.createInteractionHandle()),o._grantTimestamp=t.touchHistory.mostRecentTimeStamp,o.x0=M(t.touchHistory),o.y0=g(t.touchHistory),o.dx=0,o.dy=0,e.onResponderGrant&&e.onResponderGrant(t,o),void 0===e.onShouldBlockNativeResponder||e.onShouldBlockNativeResponder()},onResponderReject:function(t){s(n),e.onResponderReject&&e.onResponderReject(t,o)},onResponderRelease:function(t){if(o.singleTapUp&&(o._lastSingleTapUp&&i(t.touchHistory.mostRecentTimeStamp-o._lastReleaseTimestamp)<C&&(o.doubleTapUp=!0),o._lastSingleTapUp=!0,!o.doubleTapUp)){var a=babelHelpers.extends({},o),u=R.default.setTimeout(function(){o._singleTapConfirmId===u&&e.onResponderSingleTapConfirmed&&e.onResponderSingleTapConfirmed(t,a)},C);o._singleTapConfirmId=u}o._lastReleaseTimestamp=t.touchHistory.mostRecentTimeStamp,s(n),e.onResponderRelease&&e.onResponderRelease(t,o),r(o)},onResponderStart:function(n){var t=n.touchHistory;o.numberActiveTouches=t.numberActiveTouches,e.onResponderStart&&e.onResponderStart(n,o)},onResponderMove:function(n){var t=n.touchHistory;o._accountsForMovesUpTo!==t.mostRecentTimeStamp&&(a(o,t,n),e.onResponderMove&&p(e,o)&&e.onResponderMove(n,o))},onResponderEnd:function(t){var r=t.touchHistory;o.numberActiveTouches=r.numberActiveTouches,(r.numberActiveTouches>0||i(r.mostRecentTimeStamp-o._grantTimestamp)>C||Math.abs(o.dx)>=_||Math.abs(o.dy)>=_)&&(o._singleTabFailed=!0),o._singleTabFailed||(o.singleTapUp=!0),s(n),e.onResponderEnd&&e.onResponderEnd(t,o)},onResponderTerminate:function(t){s(n),e.onResponderTerminate&&e.onResponderTerminate(t,o),r(o)},onResponderTerminationRequest:function(n){return!e.onResponderTerminationRequest||e.onResponderTerminationRequest(n.gestureState)}};return babelHelpers.extends({},t)}function p(e,n){if(n.numberActiveTouches>1)return!0;var o=y;return"number"==typeof e.moveThreshold&&(o=e.minMoveDistance),Math.abs(n.dx)>=o||Math.abs(n.dy)>=o}Object.defineProperty(t,"__esModule",{value:!0}),t.default=d;var c=n(44),l=n(2367),v=babelHelpers.interopRequireDefault(l),m=n(2368),h=n(237),R=babelHelpers.interopRequireDefault(h),T=v.default.currentCentroidXOfTouchesChangedAfter,f=v.default.currentCentroidYOfTouchesChangedAfter,S=v.default.previousCentroidXOfTouchesChangedAfter,b=v.default.previousCentroidYOfTouchesChangedAfter,M=v.default.currentCentroidX,g=v.default.currentCentroidY,C=400,_=10,y=2,H=!1},2366);