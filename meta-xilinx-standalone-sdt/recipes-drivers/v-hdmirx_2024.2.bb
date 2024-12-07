
inherit features_check

REQUIRED_MACHINE_FEATURES = "v-hdmirx"

inherit esw python3native

DEPENDS += "xilstandalone video-common v-hdmi-common"

ESW_COMPONENT_SRC = "/XilinxProcessorIPLib/drivers/v_hdmirx/src/"
ESW_COMPONENT_NAME = "libv_hdmirx.a"

addtask do_generate_driver_data before do_configure after do_prepare_recipe_sysroot
do_prepare_recipe_sysroot[rdeptask] = "do_unpack"