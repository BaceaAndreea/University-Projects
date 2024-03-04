bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit, fopen,fread,fclose,printf              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll 
import fopen msvcrt.dll 
import fread msvcrt.dll   
import fclose msvcrt.dll 
import printf msvcrt.dll
 ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    nume_fisier db "andreea.txt",0
    mod_access db "r",0 ;open a file for reading 
    descriptor_fisier dd -1
    nr_carac_citite dd 0
    len equ 100 ;maximal 100 caractere zu lesen 
    zahlen times (len+1) db 0 ;string to hold the text which is read from file 
    ct times 26 db 0
    format db "%c kommt %d Mal vor",0
    maxletter dd 0
    ctmax dd 0

; our code starts here
segment code use32 class=code
    start:
        ; ...
        push dword mod_access  ;was ich mit dem datei machen wolle 
        push dword nume_fisier ;welches datei 
        call [fopen] ;um die Datei zu offnen 
        add esp,4*2
        
        mov [descriptor_fisier],eax ; store the descriptor_fisier returned by fopen
        cmp eax,0 ;wir uberprufen ob die funktion, fopen, die datei gemacht 
        je ende ;wenn nicht geht zu ende 
        
        ;eax=fread(zahlen,1,len,descriptor_fisier)
        push dword [descriptor_fisier]
        push dword len
        push dword 1
        push dword zahlen
        call [fread]
        add esp,4*4
        
        mov [nr_carac_citite],eax ;eax geht in nr_carac_citite, also geht alles was ich gelesen habe 
        mov bl,97 ;weil 97 ist 'a' in Ascii code 
        mov edi,0
        
        While:
            cmp bl,123  ;weil 'z'=122 in ascii code 
            je EndWhile ;wenn es gleich 123 dann geht zu ende weil es keine kleine Buchstaben nicht mehr existiert 
            mov esi,zahlen ;der text geht in esi
            mov ecx,[nr_carac_citite] ;ecx bekommt alles was ich von den datei gelesen habe 
            mov edx,0
            jecxz endfor ;wenn ecz gleich mit 0 ist dann geht zu endfor 
                for:
                    lodsb ;von der adresse <DS:ESI> wird ein byte ins register al geladen 
                    cmp al,bl ;wir verglichen al mit bl 
                    je if ;wenn sie gleich sind dann geht zu if 
                    jmp endif ;wenn sie nicht gleich sind dann geht zu endif 
                    if:
                    inc dl ;man incrementiert dl (wie viele mal eine Buchstabe erschienen hat)
                    endif:
                    loop for  ;
                    mov byte[ct+edi],dl ;dl wird in ct auf der Position edi getan 
                endfor:
            inc bl ;man incrementier bl bis er  123 ist und dann stopt man sich 
            inc edi ;man incredementiert edi 
        jmp While
        EndWhile:
        
        mov esi,ct ;in esi wird das vektor der erscheinungen hereingefugt
        mov ebx,97 ;ebx bekommt das erste buchstabe 97=das ist a in codul ascii 
        WhileLetter:
            cmp ebx,123 ;weil 'z'=122 in Ascii Code
            jge EndWhileLetter ;wenn >= dann geht zu ende 
            lodsb ;von der adresse <DS:ESI> wird ein byte ins register al geladen
            cmp al,byte[ctmax] 
            jle EndIfMax ;wenn es <= geht aus den ifmaxim aus 
            mov byte[ctmax],al ;wenn es gut ist dann ctmax bekommt al an
            mov dword [maxletter],ebx ;wenn es gut ist dann maxletter bekommt ebx, wo die Buchstabe ist
            EndIfMax:
            inc ebx ;ebx steigt die buchstaben
        jmp WhileLetter
        EndWhileLetter:
        
        ;eax=printf(format,maxletter,eax,ctmax)
        mov eax,0
        mov al,byte[ctmax] ;in al tuen wir die frequenz 
        push dword eax
        push dword [maxletter] ;maxletter hat die buchstabe mit der grosste frequenz
        push dword format ;we auf dem bild erscheinen muss 
        call [printf]
        add esp,4*4
        
        push dword [descriptor_fisier]
        call [fclose]
        add esp, 4
        ende:
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
