__d(function(t,n,e,r){function o(){return{onLoad:{duration:2e3,entrance:"left",beforeClipPathWidth:function(t,n,e){var r=f.default.getRange(n.props,"x"),o=r[0],i=n.props.width-r[1];return"left"===e.onLoad.entrance?{clipWidth:o+i}:"right"===e.onLoad.entrance?{clipWidth:o+i,translateX:n.props.width-o-i}:(p.default.warn("onLoad entrance should be one of left or right"),{})},afterClipPathWidth:function(t,n,e){var r=f.default.getRange(n.props,"x");return"left"===e.onLoad.entrance?{clipWidth:(0,a.sum)(r)}:"right"===e.onLoad.entrance?{clipWidth:(0,a.sum)(r),translateX:0}:(p.default.warn("onLoad entrance should be one of left or right"),{})}},onExit:{duration:500,beforeClipPathWidth:function(t,n,e){var r=(0,a.filter)(t,function(t,n){return!e[n]}),o=r.map(function(t){return n.type.getScale(n.props).x(t.x)});return(0,a.min)(o)+(0,a.max)(o)}},onEnter:{duration:500,beforeClipPathWidth:function(t,n,e){var r=(0,a.filter)(t,function(t,n){return!e[n]}),o=r.map(function(t){return n.type.getScale(n.props).x(t.x)});return(0,a.min)(o)+(0,a.max)(o)},afterClipPathWidth:function(t,n){var e=t.map(function(t){return n.type.getScale(n.props).x(t.x)});return(0,a.min)(e)+(0,a.max)(e)}}}}function i(){return{onLoad:{duration:2e3,before:function(){return{opacity:0}},after:function(t){return t}},onExit:{duration:600,before:function(){return{opacity:0}}},onEnter:{duration:600,before:function(){return{opacity:0}},after:function(t){return t}}}}Object.defineProperty(r,"__esModule",{value:!0}),r.continuousTransitions=o,r.discreteTransitions=i;var a=n(932),u=n(1088),f=babelHelpers.interopRequireDefault(u),c=n(1089),p=babelHelpers.interopRequireDefault(c)},1097);