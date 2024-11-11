
inherit features_check

REQUIRED_MACHINE_FEATURES = "hdcp22-cipher-dp"

inherit esw python3native

DEPENDS += "xilstandalone "

ESW_COMPONENT_SRC = "/XilinxProcessorIPLib/drivers/hdcp22_cipher_dp/src/"
ESW_COMPONENT_NAME = "libhdcp22_cipher_dp.a"

addtask do_generate_driver_data before do_configure after do_prepare_recipe_sysroot
do_prepare_recipe_sysroot[rdeptask] = "do_unpack"
