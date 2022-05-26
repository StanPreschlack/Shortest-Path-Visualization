<html lang="en-us">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=0.5">
    <title>Shortest Path Visualization</title>
</head>
<style>
    h1 {
        text-align: center;
        border-bottom: 2px solid black;
        padding: 10px;
    }
    p {
        padding: 2vw;
    }
    .tree,
    .tree ul,
    .tree li {
        list-style: none;
        margin: 0;
        padding: 0;
        position: relative;
    }
    .tree {
        text-align: center;
        margin: auto;
        width: 95vw;
        border: 1px solid black;
        border-radius: 15px;

    }
    .tree,
    .tree ul {
        display: table;
    }
    .tree ul {
        width: 100%;
    }
    .tree li {
        display: table-cell;
        padding: .5em 0;
        vertical-align: top;
    }
    .tree li:before {
        outline: solid 1px #000000;
        content: "";
        left: 0;
        position: absolute;
        right: 0;
        top: 0;
    }
    .tree li:first-child:before {
        left: 50%;
    }
    .tree li:last-child:before {
        right: 50%;
    }
    .tree code,
    .tree span {
        border: solid .1em #f80000;
        border-radius: .55em;
        display: inline-block;
        margin: 0 .2em .5em;
        padding: .2em .5em;
        position: relative;
        background-color: #ffffff;
        box-shadow: rgba(99, 99, 99, 0.2) 0 2px 8px 0;
        transition: 0.3s;
    }
    .tree span:hover {
        box-shadow: rgba(0, 0, 0, 0.35) 0 5px 15px;
        cursor: grab;
        border-radius: .45em;
    }
    .tree ul:before,
    .tree code:before,
    .tree span:before {
        outline: solid 1px #000000;
        content: "";
        height: .5em;
        left: 50%;
        position: absolute;
    }
    .tree ul:before {
        top: -.5em;
    }
    .tree code:before,
    .tree span:before {
        top: -.55em;
    }
    .tree>li {
        margin-top: 0;
    }
    .tree>li:before,
    .tree>li:after,
    .tree>li>code:before,
    .tree>li>span:before {
        outline: none;
    }
    footer {
        position: absolute;
        bottom: 0;
        width: 100vw;
        height: 10vh;
    }
    p {
        margin: auto;
        text-indent: 1rem;
    }
    .example {
        font-family: "Courier New", serif;
        font-size: 12pt;
        width: 75vw;
        text-indent: 0;
    }
    #result {
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        font-size: 20pt;
        color: #000000;
        margin-top: 15px;
    }
    input[type=submit] {
        width: 7rem;
        height: 1.5rem;
        border-radius: 0.75rem;
        border: none;
        background-color: black;
        transition: 0.3s;
        color: white;
        margin-left: 10px;
    }
    input[type=submit]:hover {
        background-color: #ffffff;
        cursor: grab;
        color: black;
        border: 1px solid black;
        margin-left: 11px;
    }
    input[type=text] {
        width: 20rem;
        height: 1.5rem;
        border-radius: 0.75rem;
        border: 1px solid black;
        outline: none;
    }
    form {
        position: relative;
        width: fit-content;
        left: 50%;
        transform: translateX(-50%);
    }
    #not_binary {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        z-index: 1;
        color: red;
        background-color: black;
        padding: 10px;
        border-radius: 15px;
        box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0 -12px 30px, rgba(0, 0, 0, 0.12) 0 4px 6px, rgba(0, 0, 0, 0.17) 0 12px 13px, rgba(0, 0, 0, 0.09) 0 -3px 5px;
    }
    #not_binary:hover {
        cursor: grab;
    }
</style>
<body>
        <h1>Shortest Path Visualization</h1>
        <p>Enter an expression that can be converted into a binary tree where each node has a name and a distance from the root.  <br>
            To find the distance between a node and the root of the tree add the symbol "*" at all nodes of interest. <br>
            The program will find the "*" closest to the root.
        </p>
        <p>
            For example, if you enter: "a 0 ( b 4 ( * 100 b 6 ) w 9 ( x 3 y 5 ( * 2 z 3 ) ) )" <br>
            Where "a" is the root node (which is 0 distance away from itself) and b - for example - is a child of the root node <br>
            with a distance of 4 from its parent. The program will return "found "*" at distance 16.0." <br>
            It is also important to note the space between each character as missing them will result in incorrect calculations. <br>
            <br>Some more examples to try out are: <br>
            <br>
        </p>
        <p class="example">
            a 0 ( b 1 ( * 65 c 6 ) d 9 ( e 3 f 5 ( * 2 g 3 ) ) ) <br><br>
            a 0 ( b 4 ( * 100 c 6 ) d 9 ( e 3 ( * 2 g 3 ) h 0 ( i 4 ( * 100 j 6 ) k 9 ( l 3 m 5 ( * 2 o 3 ) ) ) ) ) <br><br>
            a 0 ( b 4 ( * 100 c 6 ) d 9 ( e 3 ( * 2 g 0 ( h 1 ( * 65 i 6 ) j 9 ( k 3 l 5 ( * 2 m 3 ) ) ) ) n 0 ( o 4 ( * 100 p 6 ) q 9 ( r 3 s 5 ( * 2 u 3 ) ) ) ) ) <br>
        </p>
        <p>
            <br>
            If you are having trouble creating a valid input string take a look at the tree representation below. <br>
            The tree diagram can be used to visualize a tree of any size but it will overflow and require scrolling eventually. <br>
            This site does not currently support nodes with multiple children but will in the future.
        </p>
        <p>
            This program was built using HTML, CSS, Javascript, PHP, and Java. <br>
            The tree logic used to determine the shortest path is written in Java while the logic that creates the visual representation is in PHP. CSS and HTML are used to create and style the representation. Check out the complete source code here:
        </p>
        <br>
        <form method="get">
            <label>
                <input type="text" name="input" id="textField">
            </label>
            <input type="submit" value="build tree">
        </form>
        <ul class="tree" id="tree">
            <?php
            function createTree($input) {
                $dom_changes = "";
                $notBinary = 0;
                for ($i = 0; $i < count($input); $i++) {
                    if ($input[$i] == "(") {
                        $dom_changes .= "<ul>";
                        $notBinary = 0;
                    } else if ($input[$i] == ")") {
                        $dom_changes .= "</ul></li>";
                        $notBinary = 0;
                    } else {
                        $notBinary++;
                        if ($notBinary > 2) {
                            echo "<p id='not_binary'>CAUTION, this is not a binary tree and the answer is most likely wrong, click to ignore</p>";
                        }
                        $dom_changes .= "<li><span>" . $input[$i] . " " . $input[++$i] . "</span>";
                    }
                }
                echo "$dom_changes\n";
            }
            if (isset($_GET['input']) && $_GET['input'] != null) {
                $input = explode(" ", $_GET['input']);
                $_input_ = $input;
                for ($i = 0; $i < count($input); $i++) {
                    $input[$i] = " \"" . $input[$i] . "\" ";
                }
                exec("java -jar pathfinder.jar " . implode($input), $result);
                if ($result != null) {
                    createTree($_input_);
                    ?>
        </ul>
        <p id="result">
            <?php
                echo "<br>" . $result[0] . "<br><br><br>";
                } else {
                    echo "<br>invalid input, try making sure you have spaces between each number and character, <br> this allows the program to accommodate multi digit numbers";
                }
            } else {
                echo "Please enter an expression";
            }
            ?>
        </p>
</body>
<script>
    let notice = document.getElementById("not_binary");
    notice.addEventListener('mousedown', function() {
        notice.style.display = "none";
    })
</script>
</html>
