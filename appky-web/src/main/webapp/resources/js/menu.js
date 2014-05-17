function selectMenuitemLink(parent, link) {
    $(parent).find(".ui-state-active").removeClass("ui-state-active");
    $(link).addClass("ui-state-active");
}
