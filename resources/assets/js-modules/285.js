__d(function(t,e,o,i){"use strict";function n(t,e){return{Title:a({opacity:{type:"linear",from:t.Title.opacity,to:e.Title.opacity,min:0,max:1},left:{type:"linear",from:t.Title.left,to:e.Title.left,min:0,max:1,extrapolate:!0}}),LeftButton:a({opacity:{type:"linear",from:t.LeftButton.opacity,to:e.LeftButton.opacity,min:0,max:1,round:u},left:{type:"linear",from:t.LeftButton.left,to:e.LeftButton.left,min:0,max:1}}),RightButton:a({opacity:{type:"linear",from:t.RightButton.opacity,to:e.RightButton.opacity,min:0,max:1,round:u},left:{type:"linear",from:t.RightButton.left,to:e.RightButton.left,min:0,max:1,extrapolate:!0}})}}var a=e(286),r=e(116),l={Title:{position:"absolute",bottom:0,left:0,right:0,alignItems:"flex-start",height:56,backgroundColor:"transparent",marginLeft:72},LeftButton:{position:"absolute",top:0,left:4,overflow:"hidden",height:56,backgroundColor:"transparent"},RightButton:{position:"absolute",top:0,right:4,overflow:"hidden",alignItems:"flex-end",height:56,backgroundColor:"transparent"}},f={Left:{Title:r(l.Title,{opacity:0}),LeftButton:r(l.LeftButton,{opacity:0}),RightButton:r(l.RightButton,{opacity:0})},Center:{Title:r(l.Title,{opacity:1}),LeftButton:r(l.LeftButton,{opacity:1}),RightButton:r(l.RightButton,{opacity:1})},Right:{Title:r(l.Title,{opacity:0}),LeftButton:r(l.LeftButton,{opacity:0}),RightButton:r(l.RightButton,{opacity:0})}},u=100,p={RightToCenter:n(f.Right,f.Center),CenterToLeft:n(f.Center,f.Left),RightToLeft:n(f.Right,f.Left)};o.exports={General:{NavBarHeight:56,StatusBarHeight:0,TotalNavHeight:56},Interpolators:p,Stages:f}},285);