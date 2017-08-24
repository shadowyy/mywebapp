package codegen.javapoet.codeblock;

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

/**
 * @author shadowyy
 * @version 2017/8/23 15:26
 */
public class TestCodeBlock {
    public static void main(String[] args) throws Exception {

    }

    private void brewSetContentView1Method(TypeSpec.Builder builder)throws Exception {
        final CodeBlock.Builder codeBlock = CodeBlock.builder();


        builder.addMethod(MethodSpec.methodBuilder("setContentView").addAnnotation(Override.class).addModifiers(Modifier.PUBLIC).addParameter
                (ParameterSpec.builder(Integer.TYPE, "layoutResId").build()).addStatement("super.setContentView(layoutResId)").addCode(codeBlock
                .build()).build());


    }

    //private void brewOnClickEmitters(TypeSpec.Builder builder) {
    //    final ClassName droidkitViews = ClassName.get("droidkit.view", "Views");
    //    for (final Map.Entry<ExecutableElement, OnClick> entry : mOnClick.entrySet()) {
    //        final ExecutableElement method = entry.getKey();
    //        final List<? extends VariableElement> parameters = method.getParameters();
    //        for (final int viewId : entry.getValue().value()) {
    //            final CodeBlock.Builder codeBlock = CodeBlock.builder();
    //            codeblock.add("$L.put($T.findByIdOrThrow(this, $L), new View.OnClickListener() {\n",
    //                    M_ON_CLICK, droidkitViews, viewId);
    //            codeBlock.indent();
    //            codeBlock.add("@Override\n");
    //            codeBlock.add("public void onClick(View v) {\n");
    //            codeBlock.indent();
    //            if (parameters.isEmpty()) {
    //                codeBlock.addStatement("$L.$L()", M_DELEGATE, method.getSimpleName());
    //            } else if (parameters.size() == 1 && mTypeUtils.isSubtype(parameters.get(0), "android.view.View")) {
    //                codeBlock.addStatement("$L.$L(v)", M_DELEGATE, method.getSimpleName());
    //            } else {
    //                mEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Invalid method signature", method);
    //                throw new RuntimeException("Invalid method signature");
    //            }
    //            codeBlock.unindent();
    //            codeBlock.add("}\n");
    //            codeBlock.unindent();
    //            codeBlock.add("});\n");
    //            builder.addMethod(MethodSpec.methodBuilder("emitOnClick" + viewId)
    //                    .addModifiers(Modifier.PRIVATE)
    //                    .addCode(codeBlock.build())
    //                    .build());
    //        }
    //    }
    //}


}
