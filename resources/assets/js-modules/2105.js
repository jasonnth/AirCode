__d(function(e,t,o,r){"use strict";function u(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var o in e)Object.prototype.hasOwnProperty.call(e,o)&&(t[o]=e[o]);return t.default=e,t}Object.defineProperty(r,"__esModule",{value:!0}),r.GuidebookClickDetourOpenAppEvent=void 0;var i=t(412),n=function(e){return e&&e.__esModule?e:{default:e}}(i),a=t(694),p=u(a),d=t(698),l=u(d);r.GuidebookClickDetourOpenAppEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.Guidebook:GuidebookClickDetourOpenAppEvent:1.0.0",event_name:"guidebook_click_detour_open_app",page:"detour",operation:2,target:"open_app_button"},propTypes:{schema:n.default.string,event_name:n.default.string.isRequired,context:n.default.shape(p.Context.propTypes).isRequired,page:n.default.string.isRequired,operation:n.default.oneOf(Object.values(l.Operation)).isRequired,target:n.default.string.isRequired,detour_id:n.default.number.isRequired}}},2105);