__d(function(e,t,r,i){"use strict";function o(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&(t[r]=e[r]);return t.default=e,t}Object.defineProperty(i,"__esModule",{value:!0}),i.GuidebookClickDetourPreviewEvent=void 0;var u=t(412),n=function(e){return e&&e.__esModule?e:{default:e}}(u),d=t(694),a=o(d),l=t(698),s=o(l);i.GuidebookClickDetourPreviewEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.Guidebook:GuidebookClickDetourPreviewEvent:1.0.0",event_name:"guidebook_click_detour_preview",page:"detour",operation:2,target:"preview_button"},propTypes:{schema:n.default.string,event_name:n.default.string.isRequired,context:n.default.shape(a.Context.propTypes).isRequired,page:n.default.string.isRequired,operation:n.default.oneOf(Object.values(s.Operation)).isRequired,target:n.default.string.isRequired,detour_id:n.default.number.isRequired}}},2107);