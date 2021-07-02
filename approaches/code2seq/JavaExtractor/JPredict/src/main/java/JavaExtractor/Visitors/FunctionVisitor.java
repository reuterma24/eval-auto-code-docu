package JavaExtractor.Visitors;

import JavaExtractor.Common.CommandLineValues;
import JavaExtractor.Common.Common;
import JavaExtractor.Common.MethodContent;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

@SuppressWarnings("StringEquality")
public class FunctionVisitor extends VoidVisitorAdapter<Object> {
    private final ArrayList<MethodContent> methods = new ArrayList<>();
    private final CommandLineValues commandLineValues;

    public FunctionVisitor(CommandLineValues commandLineValues) {
        this.commandLineValues = commandLineValues;
    }

    @Override
    public void visit(MethodDeclaration node, Object arg) {
        visitMethod(node);

        super.visit(node, arg);
    }

    private void visitMethod(MethodDeclaration node) {
        LeavesCollectorVisitor leavesCollectorVisitor = new LeavesCollectorVisitor();
        leavesCollectorVisitor.visitDepthFirst(node);
        ArrayList<Node> leaves = leavesCollectorVisitor.getLeaves();

/*
        String normalizedMethodName = Common.normalizeName(node.getName(), Common.BlankWord);

        ArrayList<String> splitNameParts = Common.splitToSubtokens(node.getName());
        String splitName = normalizedMethodName;
        if (splitNameParts.size() > 0) {
            splitName = String.join(Common.internalSeparator, splitNameParts);
       }
*/
        String splitComment = "SOMEHTING-WENT-WRONG";
        boolean hasComment = false;
        if(node.getComment() != null)
        {
            hasComment = true; //this helps to ignore nested method declarations
            String comment = node.getComment().getContent();
            comment = comment.toLowerCase();
            comment = comment.replaceAll("\\*", "");
            comment = comment.replaceAll("\\\\n", "");
            String[] parts = comment.split("\\.");

            ArrayList<String> splitCommentParts = Common.splitToSubtokens(parts[0]);
            if (splitCommentParts.size() > 0)
            {
                splitComment = String.join(Common.internalSeparator, splitCommentParts);
            }
        }

        //node.setName(Common.methodName);
        node.setName(splitComment);


        if (node.getBody() != null) {
            long methodLength = getMethodLength(node.getBody().toString());

            if (commandLineValues.MaxCodeLength <= 0 || (methodLength >= commandLineValues.MinCodeLength && methodLength <= commandLineValues.MaxCodeLength))
            {
                if(hasComment == true)
                {
                    methods.add(new MethodContent(leaves, splitComment, node.toString()));
                }
            }


           /*
            if (commandLineValues.MaxCodeLength <= 0 || (methodLength >= commandLineValues.MinCodeLength && methodLength <= commandLineValues.MaxCodeLength))
            {
                methods.add(new MethodContent(leaves, splitName, node.toString()));
            }
            */
        }
    }

    private long getMethodLength(String code) {
        String cleanCode = code.replaceAll("\r\n", "\n").replaceAll("\t", " ");
        if (cleanCode.startsWith("{\n"))
            cleanCode = cleanCode.substring(3).trim();
        if (cleanCode.endsWith("\n}"))
            cleanCode = cleanCode.substring(0, cleanCode.length() - 2).trim();
        if (cleanCode.length() == 0) {
            return 0;
        }
        return Arrays.stream(cleanCode.split("\n"))
                .filter(line -> (line.trim() != "{" && line.trim() != "}" && line.trim() != ""))
                .filter(line -> !line.trim().startsWith("/") && !line.trim().startsWith("*")).count();
    }

    public ArrayList<MethodContent> getMethodContents() {
        return methods;
    }
}
