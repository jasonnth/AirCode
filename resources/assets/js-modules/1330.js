__d(function(e,t,l,a){function s(e,t){var l=e.font,a=e.bp,s=e.color,r=t.extraPaddingLeft||0,n=t.extraPaddingRight||0,o=s.graphColorScale,i=s.regular.foggy,d=l.small.fontFamily,b=l.small.size,p=5*a,f={width:450,height:300,padding:p,colorScale:o},g={fontFamily:d,fontWeight:"300",fontSize:b,letterSpacing:"normal",padding:a,fill:i,stroke:"transparent"},c=babelHelpers.extends({textAnchor:"middle"},g);return{basePadding:p,victoryTheme:{area:babelHelpers.extends({style:{data:{fill:i},labels:c}},f),axis:babelHelpers.extends({style:{axis:{fill:"none",stroke:i,strokeWidth:.5,strokeLinecap:"round",strokeLinejoin:"round"},axisLabel:babelHelpers.extends({},c,{padding:3*a}),grid:{fill:"none",stroke:"transparent"},ticks:{fill:"none",padding:a,size:1,stroke:"transparent"},tickLabels:babelHelpers.extends({},g,{padding:a})}},f),bar:babelHelpers.extends({style:{data:{fill:i,padding:a,stroke:"transparent",strokeWidth:0,width:a},labels:c}},f),candlestick:babelHelpers.extends({style:{data:{stroke:i,strokeWidth:1},labels:c},candleColors:{positive:s.white,negative:i}},f),chart:babelHelpers.extends({},f,{fill:s.white,padding:{left:6*a+r,right:3*a+n,bottom:p,top:0}}),errorbar:babelHelpers.extends({style:{data:{fill:"none",stroke:i,strokeWidth:2},labels:c}},f),group:babelHelpers.extends({colorScale:o},f),line:babelHelpers.extends({style:{data:{fill:"none",stroke:i,strokeWidth:2},labels:babelHelpers.extends({},g,{textAnchor:"start"})}},f),pie:{style:{data:{padding:a,stroke:"none",strokeWidth:1},labels:babelHelpers.extends({},c,{padding:200})},colorScale:o,width:400,height:400,padding:p},scatter:babelHelpers.extends({style:{data:{fill:i,stroke:"transparent",strokeWidth:0},labels:c}},f),stack:babelHelpers.extends({colorScale:o},f),tooltip:babelHelpers.extends({style:{data:{fill:"none",stroke:"transparent",strokeWidth:0},labels:c,flyout:{stroke:i,strokeWidth:1,fill:"#f0f0f0"}},flyoutProps:{cornerRadius:10,pointerLength:10}},f),voronoi:babelHelpers.extends({style:{data:{fill:"none",stroke:"transparent",strokeWidth:0},labels:c}},f)},barGutterWidth:{compact:a,regular:2*a},yAxisStyle:{axis:{fill:"transparent",stroke:"transparent"}},focusedLabelStyle:{fill:s.core.babu,fontWeight:"600"},navButtonStyle:babelHelpers.extends({},g,{color:s.foggy,padding:{top:a,left:r,right:n,bottom:a}}),domainPaddingExtra:a/2}}Object.defineProperty(a,"__esModule",{value:!0}),a.default=s},1330);